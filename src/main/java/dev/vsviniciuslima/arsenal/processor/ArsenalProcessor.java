package dev.vsviniciuslima.arsenal.processor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
@Getter
public abstract class ArsenalProcessor implements Processor, ArsenalExchangeProcessor {
    private Exchange exchange;

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            this.exchange = exchange;
            processExchange(exchange);
        } catch (Exception e) {
            // error handling
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public abstract void processExchange(Exchange exchange);
}
