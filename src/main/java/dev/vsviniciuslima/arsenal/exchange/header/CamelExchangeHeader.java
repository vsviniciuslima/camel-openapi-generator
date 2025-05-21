package dev.vsviniciuslima.arsenal.exchange.header;

public interface CamelExchangeHeader<T> {
    String getName();
    Class<T> getClazz();
}
