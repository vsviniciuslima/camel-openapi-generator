package dev.vsviniciuslima.application;

import dev.vsviniciuslima.application.bean.PetsPostHandler;
import dev.vsviniciuslima.arsenal.route.telemetry.TelemetryEndpointBuilderFactory;
import dev.vsviniciuslima.routePolicy.NewconErrorRoutePolicyProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ClaimCheckOperation;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spi.DataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static dev.vsviniciuslima.arsenal.route.telemetry.TelemetryEndpointBuilderFactory.telemetry;

@Component
@RequiredArgsConstructor
public class ApplicationRouteBuilder extends RouteBuilder {
    private final DataFormat jsonFormat;
    private final NewconErrorRoutePolicyProcessor newconErrorRoutePolicyProcessor;
    private final PetsPostHandler petsPost;

    @Override
    public void configure() {

        // @Controller
        restConfiguration()
            .component("servlet")
//            .host("localhost")
            .contextPath("/api")
            .port(8081);

        // @Get / Controller
        rest("/pets")
            .post()
            .id("create-pet")
            .type(ApiRequest.class)
            .bindingMode(RestBindingMode.json)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .to("direct:create-pet");

        // @ControllerAdvice
        onException(Exception.class)
            .log(LoggingLevel.ERROR, "(onException) Erro ao processar a rota ${routeId}: ${exception.message}")
            .unmarshal(jsonFormat)
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400));
//            .end();

        // Service

        var requestTelemetry = telemetry("Iniciando criação de pets")
                .eventData(Map.of(
                        "name", "${exchangeProperty.name}",
                        "fail", "${header.fail}"
                ));

        var responseTelemetry = telemetry("Resposta da criação de pets")
                .eventData("enginesAnalyzisResult", "${exchangeProperty.enginesAnalyzisResult}")
                .eventData("name", "${body.name}")
                .eventData("fail", "${header.fail}")
                .includeBody(true);

        from("direct:create-pet-v2")
            .routeId("create-pet-v2-service")
            .setProperty("telemetryJourney", constant("Criação de pet"))
            .to(requestTelemetry)
            .bean(petsPost)
            .to(responseTelemetry)
            .end();

        from("direct:create-pet")
            .routeId("create-pet-service")
            .marshal(jsonFormat)
            .removeHeader(Exchange.HTTP_URI)
            .setHeader("bridgeEndpoint", constant(true))
            .setHeader(Exchange.HTTP_METHOD, constant("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .setHeader("Accept", constant("application/json"))
            .setProperty("fail", header("fail"))
            .removeHeader("fail")
            .log(LoggingLevel.INFO, "Chamando endpoint /pets?fail=${exchangeProperty.fail} com o corpo ${body}")
            .toD("{{api.base-url}}/pets?fail=${exchangeProperty.fail}&bridgeEndpoint=true")
            .convertBodyTo(String.class)
            .to(telemetry("Iniciando criação de pets"))
            .to("petsApi:postPets?bridgeEndpoint=true")
                .to("telemetry:Chamada ao endpoint /pets")
                .claimCheck(ClaimCheckOperation.Set)
            .to(telemetry("Resposta da criação de pets")
                    .journey("${exchangeProperty.telemetryJourney}")
//                    .eventData("${exchangeProperty.enginesAnalyzisResult}")
                    .includeBody(true))
            .process(newconErrorRoutePolicyProcessor)
            .log(LoggingLevel.INFO, "Resposta recebida: ${body}")
            .unmarshal(jsonFormat)
            .end();
    }

}
