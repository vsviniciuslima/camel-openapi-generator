package dev.vsviniciuslima.arsenal.exchange.property;

public interface CamelExchangeProperty<T> {
    String getName();
    Class<T> getClazz();
}
