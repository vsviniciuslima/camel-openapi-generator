package dev.vsviniciuslima.arsenal.exchange.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CamelExchangePropertyImpl<T> implements CamelExchangeProperty<T> {
    private final String name;
    private final Class<T> clazz;
}