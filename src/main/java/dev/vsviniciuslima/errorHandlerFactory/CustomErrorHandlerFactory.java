package dev.vsviniciuslima.errorHandlerFactory;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomErrorHandlerFactory {

    public ErrorHandlerFactory createErrorHandlerBuilder() {
        return new DeadLetterChannelBuilder("seda:capturarErroNewcon")
                .useOriginalMessage()
                .maximumRedeliveries(1)
                .logHandled(false)
                .onPrepareFailure(exchange -> {
                    // Preparar a exchange antes de enviá-la para o dead letter channel
                    exchange.setProperty("errorTimestamp", System.currentTimeMillis());
                    exchange.setProperty("originalRouteId", exchange.getFromRouteId());

                    log.info("Erro detectado na rota {}, encaminhando para processamento", exchange.getFromRouteId());
                });
    }
}
