package dev.vsviniciuslima.routePolicy;

import dev.vsviniciuslima.errorHandlerFactory.CustomErrorHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.support.RoutePolicySupport;

@Slf4j
@RequiredArgsConstructor
public class NewconErrorPolicy extends RoutePolicySupport {
    private final String routeId;
    private final CustomErrorHandlerFactory customErrorHandlerFactory;

    @Override
    public void onInit(Route route) {
        log.info("Adicionando NewconErrorHandlerFactory via NewconErrorPolicy na rota {}", routeId);
        route.addErrorHandler(customErrorHandlerFactory.createErrorHandlerBuilder(), route.getRoute());
        super.onInit(route);
    }

    @Override
    public void onExchangeDone(Route route, Exchange exchange) {
        final Integer statusCode = exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        final Throwable exceptionCaught = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);

        boolean isHttpError = statusCode != null && statusCode >= 400 || exceptionCaught instanceof HttpOperationFailedException;
        if (!isHttpError) return;

        log.debug("Erro de integração HTTP detectado na rota {}, enviado para processamento", route.getId());

        exchange.getContext()
                .createProducerTemplate()
                .send("direct:handleNewconError", exchange.copy());
//                    .asyncSend("direct:handleNewconError", copy);
    }

}
