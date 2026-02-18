package dev.vsviniciuslima.arsenal.route.telemetry;

import dev.vsviniciuslima.arsenal.route.telemetry.domain.EventType;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.AbstractEndpointBuilder;

import java.util.HashMap;
import java.util.Map;

public final class TelemetryEndpointBuilderFactory {

    private TelemetryEndpointBuilderFactory() {}

    public static TelemetryEndpointBuilder telemetry(String spanName) {
        return new TelemetryEndpointBuilder(spanName);
    }

    public static class TelemetryEndpointBuilder extends AbstractEndpointBuilder implements EndpointProducerBuilder {

        public TelemetryEndpointBuilder(String spanName) {
            super("telemetry", spanName);
        }

        public TelemetryEndpointBuilder eventType(EventType eventType) {
            doSetProperty("eventType", eventType);
            return this;
        }

        public TelemetryEndpointBuilder includeBody(boolean includeBody) {
            doSetProperty("includeBody", includeBody);
            return this;
        }

        public TelemetryEndpointBuilder journey(String journey) {
            doSetProperty("journey", journey);
            return this;
        }

        public TelemetryEndpointBuilder eventData(Map<String, String> eventData) {
            // Sempre cria uma cópia mutável para evitar modificações externas
            doSetProperty("eventData", new HashMap<>(eventData));
            return this;
        }

//        public TelemetryEndpointBuilder eventData(String key, String expression) {
//            doSetProperty("eventData", Map.of(key, expression));
//            return this;
//        }

        public TelemetryEndpointBuilder eventData(String key, String expression) {
            // Recupera o mapa atual
            Object current = this.properties.get("eventData");

            Map<String, String> eventDataMap;
            if (current instanceof Map) {
                // ✅ Cria cópia do mapa existente
                eventDataMap = new HashMap<>((Map<String, String>) current);
            } else {
                eventDataMap = new HashMap<>();
            }

            // ✅ Adiciona o novo valor
            eventDataMap.put(key, expression);

            // ✅ Substitui o mapa completo
            doSetProperty("eventData", eventDataMap);
            return this;
        }
    }
}

