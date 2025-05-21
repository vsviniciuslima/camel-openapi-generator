package dev.vsviniciuslima.arsenal.exchange.property;

import dev.vsviniciuslima.arsenal.domain.Event;

public interface TelemetryExchangeProperties<T> extends CamelExchangeProperty<T> {
    CamelExchangeProperty<Event> EVENT = new CamelExchangePropertyImpl<>("event", Event.class);
}
