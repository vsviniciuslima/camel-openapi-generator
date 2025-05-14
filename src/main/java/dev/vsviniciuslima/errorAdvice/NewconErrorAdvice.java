//package dev.vsviniciuslima.NewconErrorAdvice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.camel.CamelContext;
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.AdviceWith;
//import org.apache.camel.model.ModelCamelContext;
//import org.apache.camel.model.RouteDefinition;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Implementação baseada em AdviceWith que modifica as rotas existentes
// * para adicionar interceptação de chamadas HTTP para hosts específicos.
// */
////@Slf4j
//@Component
//public class NewconErrorAdvice implements ApplicationListener<ApplicationReadyEvent> {
//
//    private final CamelContext camelContext;
//
//    @Value("${newcon.error-handler.target-hosts:newcon-api}")
//    private String[] targetHosts;
//
//    @Value("${newcon.error-handler.enabled:true}")
//    private boolean enabled;
//
//    private Logger log;
//
//    // Rastrear rotas já processadas
//    private final Set<String> processedRoutes = ConcurrentHashMap.newKeySet();
//
//    public NewconErrorAdvice(CamelContext camelContext) {
//        this.camelContext = camelContext;
//    }
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        if (!enabled) {
//            log.info("NewconErrorAdvice desabilitado via configuração");
//            return;
//        }
//
//        try {
//            log.info("Aplicando advice para interceptação de erros Newcon. Hosts monitorados: {}",
//                    Arrays.toString(targetHosts));
//
//            // Aplicar advice às rotas existentes
//            applyAdviceToRoutes();
//        } catch (Exception e) {
//            log.error("Erro ao aplicar advice para interceptação de erros Newcon", e);
//        }
//    }
//
//    /**
//     * Aplica advice a todas as rotas existentes
//     */
//    private void applyAdviceToRoutes() {
//
//        ModelCamelContext modelContext = camelContext.adapt(ModelCamelContext.class);
//        for (RouteDefinition route : modelContext.getRouteDefinitions()) {
//            String routeId = route.getId();
//
//            // Não aplicar às nossas próprias rotas de erro ou rotas já processadas
//            if (routeId.equals("handleNewconError") ||
//                    routeId.equals("consultarErroNewcon") ||
//                    routeId.equals("logErroNewcon") ||
//                    processedRoutes.contains(routeId)) {
//                continue;
//            }
//
//            try {
//                // Verificar se a rota contém chamadas HTTP para hosts Newcon
//                if (containsNewconHttpCalls(route)) {
//                    log.debug("Aplicando advice para interceptação de erros à rota: {}", routeId);
//
//                    AdviceWith.adviceWith(camelContext, routeId, advice -> {
//                        // Interceptar chamadas HTTP para hosts Newcon
//                        for (String host : targetHosts) {
//                            advice.interceptSendToEndpoint("http*://*" + host + "*")
//                                    .afterEach()
//                                    .choice()
//                                    .when(simple("${header.CamelHttpResponseCode} >= 400"))
//                                    .log(LoggingLevel.DEBUG,
//                                            "Interceptada resposta HTTP de erro: ${header.CamelHttpResponseCode}")
//                                    .wireTap("seda:capturarErroNewcon")
//                                    .end();
//                        }
//
//                        // Também adicionar manipulador de exceção para erros relacionados ao Newcon
//                        advice.onException(Exception.class)
//                                .when(exceptionMessage().contains(String.join("|", targetHosts)))
//                                .handled(false)  // Não interferir com o fluxo normal da rota
//                                .continued(true) // Permitir que a rota continue
//                                .log(LoggingLevel.DEBUG, "Exceção Newcon interceptada: ${exception.message}")
//                                .wireTap("seda:capturarErroNewcon")
//                                .end();
//                    });
//
//                    // Marcar como processado
//                    processedRoutes.add(routeId);
//                    log.info("Advice aplicado com sucesso à rota: {}", routeId);
//                }
//            } catch (Exception e) {
//                log.warn("Não foi possível aplicar advice à rota {}: {}", routeId, e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Verifica se a rota contém chamadas HTTP para hosts Newcon
//     */
//    private boolean containsNewconHttpCalls(RouteDefinition route) {
//        String routeStr = route.toString();
//
//        // Verificar se a rota contém chamadas HTTP
//        boolean containsHttp = routeStr.contains("http:") ||
//                routeStr.contains("https:") ||
//                routeStr.contains("rest:") ||
//                routeStr.contains("restlet:");
//
//        if (!containsHttp) {
//            return false;
//        }
//
//        // Verificar se a rota contém hosts alvo
//        for (String host : targetHosts) {
//            if (routeStr.contains(host)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}