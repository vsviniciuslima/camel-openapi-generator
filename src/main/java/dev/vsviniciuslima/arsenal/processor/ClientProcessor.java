package dev.vsviniciuslima.arsenal.processor;

import dev.vsviniciuslima.arsenal.domain.Channel;
import dev.vsviniciuslima.arsenal.domain.Event;
import dev.vsviniciuslima.arsenal.exchange.header.RequestExchangeHeaders;
import dev.vsviniciuslima.arsenal.exchange.property.RequestDataExchangeProperties;
import dev.vsviniciuslima.arsenal.exchange.property.TelemetryExchangeProperties;
import org.apache.camel.Exchange;

import java.util.Optional;

public class ClientProcessor extends ArsenalProcessor {

    @Override
    public void processExchange(Exchange exchange) {
        Optional<Event> telemetryEvent = findProperty(TelemetryExchangeProperties.EVENT);

        Event now = getPropertyOrDefault(TelemetryExchangeProperties.EVENT, new Event("now"));
        String propertyOrDefault = getPropertyOrDefault("foo", String.class, "bar");
        Event event = getPropertyOrThrow("event", Event.class);

        Channel channelUnchecked = getPropertyUnchecked(RequestDataExchangeProperties.CHANNEL);

        Optional<Channel> header = findHeader(RequestExchangeHeaders.CHANNEL);


        // Nao compila porque o objeto é validado pela tipagem da interface
//        setProperty(RequestDataExchangeProperties.CHANNEL, new Object());

        setProperty(RequestDataExchangeProperties.CHANNEL, channelUnchecked);
    }
}
