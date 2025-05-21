package dev.vsviniciuslima.arsenal.processor;

import dev.vsviniciuslima.arsenal.exchange.header.CamelExchangeHeader;
import dev.vsviniciuslima.arsenal.exchange.property.CamelExchangeProperty;
import dev.vsviniciuslima.arsenal.exchange.property.RequestDataExchangeProperties;
import dev.vsviniciuslima.arsenal.exchange.property.TelemetryExchangeProperties;
import org.apache.camel.Exchange;
import org.apache.camel.Experimental;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface ArsenalExchangeProcessor extends Processor {
    Logger logger = LoggerFactory.getLogger(ArsenalExchangeProcessor.class);
    Exchange getExchange();

    default <T> T getBody(Class<T> type) {
        return getExchange().getMessage().getBody(type);
    }

    default void setBody(Object body) {
        getExchange().getMessage().setBody(body);
    }

    default <T> T getHeader(String name, Class<T> type) {
        return getExchange().getMessage().getHeader(name, type);
    }

    default <T> T getPropertyOrDefault(CamelExchangeProperty<T> key, T defaultValue) {
        return Optional
                .ofNullable(getExchange().getProperty(key.getName(), key.getClazz()))
                .orElse(defaultValue);
    }

    /**
     * Este método retorna um opcional da propriedade do tipo especificado.
     *
     * <p>Exemplo de uso:</p>
     * <pre>{@code
     * Event event = processor.getPropertySafe("event", Event.class);
     * }</pre>
     *
     * @param key a enumeração que representa a propriedade disponível em um {@link CamelExchangeProperty},
     *            como {@link RequestDataExchangeProperties} ou {@link TelemetryExchangeProperties}.
     * @see RequestDataExchangeProperties
     * @see TelemetryExchangeProperties
     * @return um opcional daa propriedade do tipo especificado
     */
    default <T> Optional<T> findProperty(CamelExchangeProperty<T> key) {
        T value = getExchange().getProperty(key.getName(), key.getClazz());
        return Optional.ofNullable(value);
    }

    default <T> Optional<T> findProperty(String name, Class<T> type) {
        return Optional.ofNullable(getExchange().getProperty(name, type));
    }

    default <T> void setProperty(CamelExchangeProperty<T> key, T value) {
        getExchange().setProperty(key.getName(), value);

        Optional.ofNullable(value)
                .ifPresentOrElse(val -> logger.debug("Setting property {} as {}", key.getName(), val),
                        () -> logger.warn("Property {} is being set as null", key.getName())
                );
    }

    @Experimental
    default <T> void setNonNullableProperty(CamelExchangeProperty<T> key, T value) {
        Optional.ofNullable(value)
                .ifPresentOrElse(val -> {
                    getExchange().setProperty(key.getName(), value);
                    logger.debug("Setting property {} as {}", key.getName(), val);
                }, () -> {
                    throw new RuntimeException("Property " + key.getName() + " cannot be null");
                });
    }

    default <T> Optional<T> findHeader(CamelExchangeHeader<T> key) {
        T value = getExchange().getMessage().getHeader(key.getName(), key.getClazz());
        return Optional.ofNullable(value);
    }

    default <T> T getPropertyUnchecked(CamelExchangeProperty<T> key) {
        T value = getExchange().getProperty(key.getName(), key.getClazz());

        if(value == null) {
            logger.warn("Property {} is being read as null", key.getName());
        }

        return value;
    }

    default <T> T getPropertyOrThrow(CamelExchangeProperty<T> key) {
        return Optional
                .ofNullable(getExchange().getProperty(key.getName(), key.getClazz()))
                .orElseThrow();
    }

    /**
     * Este método retorna uma propriedade do tipo especificado.
     *
     * <p>Exemplo de uso:</p>
     * <pre>{@code
     * Event event = processor.getPropertySafe("event", Event.class);
     * }</pre>
     *
     * @param name o nome da propriedade
     * @param type a classe do tipo da propriedade
     * @param <T> o tipo da propriedade
     * @return a propriedade do tipo especificado
     * @throws IllegalStateException se a propriedade não for encontrada
     */
    default <T> T getPropertyOrThrow(String name, Class<T> type) {
        return Optional
                .ofNullable(getExchange().getProperty(name, type))
                .orElseThrow();
    }

    default <T> T getPropertyOrDefault(String name, Class<T> type, T defaultValue) {
        T value = getExchange().getProperty(name, type);
        return value != null ? value : defaultValue;
    }

    default <T> T getPropertyUnchecked(String name, Class<T> type) {
        return getExchange().getProperty(name, type);
    }

}
