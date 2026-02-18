package dev.vsviniciuslima.arsenal.route.telemetry;

import dev.vsviniciuslima.arsenal.route.telemetry.domain.EventType;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;
import org.apache.camel.spi.UriParam;
import org.apache.camel.support.DefaultEndpoint;

import java.util.Map;

/**
 * Componente interno de telemetria para envio de eventos padronizados.
 */
@UriEndpoint(
        firstVersion = "1.0.0",
        scheme = "telemetry",
        title = "Telemetry",
        syntax = "telemetry:journey",
        category = { Category.MONITORING }
)
public class TelemetryEndpoint extends DefaultEndpoint {

    // getters e setters
    @Setter
    @Getter
    @UriPath(description = "Nome da jornada de telemetria")
    private String journey;

    @Setter
    @Getter
    @UriPath(description = "Nome do span de telemetria")
    private String spanName;

    @Setter
    @Getter
    @UriParam(description = "Mapa de expressões para dados do evento")
    private Map<String, String> eventData;

    @Setter
    @Getter
    @UriParam(description = "Tipo do evento")
    private EventType eventType;

    @Setter
    @Getter
    @UriParam(description = "Indica se o corpo da mensagem deve ser incluído nos dados do evento")
    private boolean includeBody;

    public TelemetryEndpoint(String endpointUri, TelemetryComponent component) {
        super(endpointUri, component);
    }

    @Override
    public Producer createProducer() {
        return new TelemetryProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException("Consumer not supported");
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


}

