//package dev.vsviniciuslima.application;
//
//import dev.vsviniciuslima.routePolicy.NewconErrorRoutePolicyProcessor;
//import lombok.RequiredArgsConstructor;
//import org.apache.camel.Exchange;
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.rest.RestBindingMode;
//import org.apache.camel.spi.DataFormat;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class ApplicationRoute extends RouteBuilder {
//    private final DataFormat jsonFormat;
//    private final NewconErrorRoutePolicyProcessor newconErrorRoutePolicyProcessor;
//
//    @Override
//    public void configure() {
//
//        // @Controller
//        restConfiguration()
//            .component("servlet")
////            .host("localhost")
//            .contextPath("/api")
//            .port(8081);
//
//        // @Get / Controller
//        rest("/pets")
//            .post()
//            .id("create-pet")
//            .type(ApiRequest.class)
//            .bindingMode(RestBindingMode.json)
//            .produces(MediaType.APPLICATION_JSON_VALUE)
//            .to("direct:create-pet");
//
//        // @ControllerAdvice
//        onException(Exception.class)
//            .log(LoggingLevel.ERROR, "(onException) Erro ao processar a rota ${routeId}: ${exception.message}")
//            .unmarshal(jsonFormat)
//            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400));
////            .end();
//
//        // Service
//        from("direct:create-pet")
//            .doTry()
//                .routeId("create-pet-service")
//                .marshal(jsonFormat)
//                .removeHeader(Exchange.HTTP_URI)
//                .setHeader("bridgeEndpoint", constant(true))
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .setHeader("Accept", constant("application/json"))
//                .setProperty("fail", header("fail"))
//                .removeHeader("fail")
//                .log(LoggingLevel.INFO, "Chamando endpoint /pets?fail=${exchangeProperty.fail} com o corpo ${body}")
//                .toD("{{api.base-url}}/pets?fail=${exchangeProperty.fail}&bridgeEndpoint=true")
//                .convertBodyTo(String.class)
//                .process(newconErrorRoutePolicyProcessor)
//                .log(LoggingLevel.INFO, "Resposta recebida: ${body}")
//                .unmarshal(jsonFormat)
//            .doCatch(Exception.class)
////            .filter(simple("${body} contains 'instance'"))
////                .to("log:foo")
//            .end();
//    }
//
//}
