package dev.vsviniciuslima.routePolicy.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vsviniciuslima.routePolicy.NewconErrorRoutePolicyProcessor;
import dev.vsviniciuslima.client.model.PetsPost400Response;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandleNewconErrorRoute extends RouteBuilder {
    private final ObjectMapper objectMapper;
    private final NewconErrorRoutePolicyProcessor newconErrorRoutePolicyProcessor;

    @Value("${newcon.error-handler.enabled:true}")
    private boolean enabled;

    @Value("${newcon.error-handler.servidor1-url:https://servidor1/api/erros}")
    private String servidor1Url;

    @Value("${newcon.error-handler.servidor2-url:https://servidor2/api/erros}")
    private String servidor2Url;


    @Override
    public void configure() {

        from("direct:handleNewconError")
                .routeId("handleNewconError")
                .setProperty("disableNewconHttpInterceptor", constant(true))
//                .filter(exchange -> exchange.getMessage().getHeader("CamelHttpResponseCode", int.class) >= 400)
                .log(LoggingLevel.INFO, "Processando erro Newcon")
                .process(exchange -> {
                    // Extrair modelo de erro ou ID
                    PetsPost400Response error = extractNewconError(exchange);
                    if (error != null) {
                        // Se já temos o modelo completo, usá-lo
                        exchange.getMessage().setBody(error);
                    } else {
                        // Caso contrário, extrair apenas o ID
                        String errorId = extractErrorId(exchange);
                        if (errorId != null) {
                            // Configurar cabeçalhos para processamento assíncrono
                            exchange.getMessage().setHeader("errorId", errorId);
                            exchange.getMessage().setHeader("routeId", exchange.getFromRouteId());
                            exchange.getMessage().setHeader("timestamp", System.currentTimeMillis());
                        }
                    }
                })
                .choice()
                .when(body().isInstanceOf(PetsPost400Response.class))
                    .to("direct:logErroNewcon")
//                    .to("seda:consultarErroNewcon?multipleConsumers=true&concurrentConsumers=5")
                .when(header("errorId").isNotNull())
                    .log(LoggingLevel.DEBUG, "Erro com ID ${header.errorId} enviado para consulta")
                .otherwise()
                .log(LoggingLevel.INFO, "Não foi possível extrair informações de erro Newcon")
                .end();

        // Rota para logging e processamento final
        from("direct:logErroNewcon")
                .routeId("logErroNewcon")
                .to("log:NewconErrorLogger" +
                        "?showBody=true" +
                        "&showHeaders=true" +
                        "&multiline=true" +
                        "&skipBodyLineSeparator=false" +
                        "&showStreams=true" +
                        "&showBodyType=true" +
                        "&showExchangePattern=false" +
                        "&level=ERROR" +
                        "&showProperties=true" +
                        "&showCaughtException=true"
                )
                .process(newconErrorRoutePolicyProcessor)
//                .log(LoggingLevel.INFO, "Erro Newcon: [${body.errorId}] ${body.message} (${body.errorCode})")
                // Aqui você pode adicionar lógica para persistir, gerar métricas, alertas, etc.
                .end();

        // Rota assíncrona para consultar detalhes do erro
//        from("seda:consultarErroNewcon?concurrentConsumers=5")
//        from("seda:consultarErroNewcon")
//                .routeId("consultarErroNewcon")
//                .log(LoggingLevel.DEBUG, "Consultando detalhes para erro ID: ${header.errorId}")
//                .process(exchange -> {
//                    String errorId = exchange.getMessage().getHeader("errorId", String.class);
//                    if (errorId != null) {
//                        NewconError errorDetails = consultarErroNewcon(errorId);
//                        exchange.getMessage().setBody(errorDetails);
//                    }
//                })
//                .choice()
//                .when(body().isNotNull())
//                .to("direct:logErroNewcon")
//                .otherwise()
//                .log(LoggingLevel.WARN, "Não foi possível obter detalhes para o erro ID: ${header.errorId}")
//                .end();


    }

    /**
     * Tenta extrair um objeto NewconError da exchange
     */
    private PetsPost400Response extractNewconError(Exchange exchange) {
        try {

            try {
                final HttpOperationFailedException exceptionCaught =
                        exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
                log.info("HttpOperationFailedException captured, trying to read body");
                String stringResponseBody = exceptionCaught.getResponseBody();
                return new ObjectMapper().readValue(stringResponseBody, PetsPost400Response.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Verificar se o corpo já é um NewconError
            Object body = exchange.getMessage().getBody();
            if (body instanceof PetsPost400Response) {
                return (PetsPost400Response) body;
            }

            // Tentar extrair de String JSON
            if (body instanceof String) {
                try {
                    return objectMapper.readValue((String) body, PetsPost400Response.class);
                } catch (Exception e) {
                    // Não é um NewconError válido
                }
            }

            // Tentar extrair da exceção
            Exception exception = exchange.getException();
            if (exception instanceof HttpOperationFailedException) {
                HttpOperationFailedException httpEx = (HttpOperationFailedException) exception;
                String responseBody = httpEx.getResponseBody();
                if (responseBody != null) {
                    try {
                        return objectMapper.readValue(responseBody, PetsPost400Response.class);
                    } catch (Exception e) {
                        // Não é um NewconError válido
                    }
                }
            }
        } catch (Exception e) {
            log.debug("Erro ao extrair PetsPost400Response: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Extrai apenas o ID do erro quando não temos o objeto completo
     */
    private String extractErrorId(Exchange exchange) {
        try {
            // Tentar extrair do corpo
            Object body = exchange.getMessage().getBody();
            if (body instanceof String) {
                try {
                    Map<String, Object> map = objectMapper.readValue((String) body, Map.class);
                    Object errorId = map.get("errorId");
                    if (errorId != null) {
                        return errorId.toString();
                    }
                } catch (Exception e) {
                    // Não é um JSON válido
                }
            } else if (body instanceof Map) {
                Object errorId = ((Map<?, ?>) body).get("errorId");
                if (errorId != null) {
                    return errorId.toString();
                }
            }

            // Tentar extrair da exceção
            Exception exception = exchange.getException();
            if (exception instanceof HttpOperationFailedException) {
                HttpOperationFailedException httpEx = (HttpOperationFailedException) exception;
                String responseBody = httpEx.getResponseBody();
                if (responseBody != null) {
                    try {
                        Map<String, Object> map = objectMapper.readValue(responseBody, Map.class);
                        Object errorId = map.get("errorId");
                        if (errorId != null) {
                            return errorId.toString();
                        }
                    } catch (Exception e) {
                        // Não é um JSON válido
                    }
                }
            }

            // Tentar cabeçalho específico
            String headerErrorId = exchange.getMessage().getHeader("X-Error-Id", String.class);
            if (headerErrorId != null) {
                return headerErrorId;
            }
        } catch (Exception e) {
            log.debug("Erro ao extrair ID de erro: {}", e.getMessage());
        }

        return null;
    }

//    /**
//     * Consulta os detalhes de um erro Newcon nos servidores configurados
//     */
//    private NewconError consultarErroNewcon(String errorId) {
//        // Primeiro tentar no servidor 1
//        try {
//            log.debug("Consultando erro {} no servidor 1", errorId);
//            NewconError error = consultarErroServidor(servidor1Url + "/" + errorId);
//            if (error != null) {
//                log.debug("Erro {} encontrado no servidor 1", errorId);
//                error.setInstance("servidor1");
//                return error;
//            }
//        } catch (Exception e) {
//            log.debug("Falha ao consultar servidor 1 para erro {}: {}", errorId, e.getMessage());
//        }
//
//        // Tentar no servidor 2 se não encontrou no 1
//        try {
//            log.debug("Consultando erro {} no servidor 2", errorId);
//            NewconError error = consultarErroServidor(servidor2Url + "/" + errorId);
//            if (error != null) {
//                log.debug("Erro {} encontrado no servidor 2", errorId);
//                error.setInstance("servidor2");
//                return error;
//            }
//        } catch (Exception e) {
//            log.debug("Falha ao consultar servidor 2 para erro {}: {}", errorId, e.getMessage());
//        }
//
//        log.warn("Não foi possível encontrar detalhes do erro {} em nenhum servidor", errorId);
//        return null;
//    }

//    /**
//     * Consulta um servidor específico por detalhes de erro
//     */
//    private NewconError consultarErroServidor(String url) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//
////         Configurar timeout
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(3000)
//                .setSocketTimeout(3000)
//                .build();
//        httpGet.setConfig(requestConfig);
//
//        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//            int statusCode = response.getStatusLine().getStatusCode();
//
//            if (statusCode >= 200 && statusCode < 300) {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    String responseBody = EntityUtils.toString(entity);
//                    return objectMapper.readValue(responseBody, NewconError.class);
//                }
//            }
//        }
//
//        return null;
//    }

}
