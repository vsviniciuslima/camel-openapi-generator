package dev.vsviniciuslima.arsenal.route.telemetry;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.arsenal.route.telemetry
 * <p>
 * User: Vinicius
 * Date: 2/16/2026
 * Time: 1:16 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class TelemetryProducer extends DefaultProducer {

    private final TelemetryEndpoint endpoint;

    public TelemetryProducer(TelemetryEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        String journey = resolveJourney(exchange);
        Map<String, Object> eventData = resolveEventData(exchange);

        // Aqui entraria a chamada real de telemetria
        System.out.println("Telemetry -> " +
                " journey=" + journey +
                " eventData=" + eventData
        );
    }

    private String resolveJourney(Exchange exchange) {
        return notNullEmptyOrBlank(endpoint.getJourney())
                .or(() -> notNullEmptyOrBlank(exchange.getProperty("telemetryJourney", String.class)))
                .or(() -> notNullEmptyOrBlank(exchange.getContext().getRoute(exchange.getFromRouteId()).getDescription()))
                .map(String::trim)
                .map(journey -> exchange.getContext()
                        .resolveLanguage("simple")
                        .createExpression(journey)
                        .evaluate(exchange, String.class)
                ).orElseThrow(() -> new IllegalArgumentException("Journey must be defined either as endpoint parameter or exchange property"));
    }

    private Optional<String> notNullEmptyOrBlank(String value) {
        return Optional.ofNullable(value)
                .filter(v -> !v.trim().isEmpty());
    }

    private Map<String, Object> resolveEventData(Exchange exchange) {
        Map<String, String> eventDataExpressions = endpoint.getEventData();

        // Se não definiu nada e includeBody=true, usa o body
        if ((eventDataExpressions == null || eventDataExpressions.isEmpty())
                && endpoint.isIncludeBody()) {
            return Map.of("body", exchange.getIn().getBody());
        }

        if (eventDataExpressions == null || eventDataExpressions.isEmpty()) {
            return Map.of();
        }

        // Avalia cada expressão e retorna um mapa de resultados
        return eventDataExpressions.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> exchange.getContext()
                                .resolveLanguage("simple")
                                .createExpression(entry.getValue())
                                .evaluate(exchange, Object.class)
                ));
    }

    private String serializeEventData(Object data) {
        if (data == null) return "{}";
        if (data instanceof String) return (String) data;

        try {
            return endpoint.getCamelContext()
                    .getTypeConverter()
                    .mandatoryConvertTo(String.class, data);
        } catch (Exception e) {
            return data.toString();
        }
    }
}

