//package dev.vsviniciuslima.errorAdvice;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.camel.CamelContext;
//import org.apache.camel.Exchange;
//import org.apache.camel.builder.AdviceWith;
//import org.apache.camel.http.base.HttpOperationFailedException;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.apache.camel.builder.Builder.header;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class RouteAdviceConfigurer implements ApplicationListener<ApplicationReadyEvent> {
//    private final CamelContext camelContext;
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        List<String> whitelist = List.of("Error", "handleNewconError", "logErroNewcon");
//        camelContext.getRoutes()
//                .stream()
//                .filter(route -> Optional
//                        .ofNullable(route.getProperties().get("disableNewconHttpInterceptor"))
//                        .map(opt -> Boolean.parseBoolean(opt.toString()))
//                        .orElse(true)
//                )
//                .forEach(route -> {
//                    try {
//                        AdviceWith.adviceWith(camelContext,route.getId(), advice -> {
//                            advice.interceptSendToEndpoint("http://*")
//                                    .when(header(Exchange.HTTP_URI).not().contains("logs"))
//                                    .log("Interceptando requisição http na rota ${routeId}")
//                                    .afterUri("direct:handleNewconError");
//
//                            advice.onException(HttpOperationFailedException.class)
//                                .log("Interceptando erro HTTP ${exception.message}")
//                                .handled(false);
//
//                            log.info("Advice aplicado à rota: {}", route.getId());
//                        });
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//    }
//
//
//}
