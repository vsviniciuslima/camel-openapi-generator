package dev.vsviniciuslima.arsenal.exchange.property;

import dev.vsviniciuslima.arsenal.domain.Channel;

public interface RequestDataExchangeProperties<T> extends CamelExchangeProperty<T> {
    CamelExchangeProperty<Channel> CHANNEL = new CamelExchangePropertyImpl<>("channel.key", Channel.class);
}
