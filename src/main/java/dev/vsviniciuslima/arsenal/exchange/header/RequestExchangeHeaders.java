package dev.vsviniciuslima.arsenal.exchange.header;

import dev.vsviniciuslima.arsenal.domain.Channel;

public interface RequestExchangeHeaders<T> extends CamelExchangeHeader<T> {
    CamelExchangeHeader<Channel> CHANNEL = new CamelExchangeHeaderImpl<>("channel.key", Channel.class);
}
