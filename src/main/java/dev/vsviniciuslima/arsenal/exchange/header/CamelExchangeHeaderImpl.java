package dev.vsviniciuslima.arsenal.exchange.header;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CamelExchangeHeaderImpl<T> implements CamelExchangeHeader<T> {
    private final String name;
    private final Class<T> clazz;
}