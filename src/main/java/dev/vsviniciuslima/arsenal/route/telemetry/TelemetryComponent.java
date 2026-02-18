package dev.vsviniciuslima.arsenal.route.telemetry;

import org.apache.camel.Endpoint;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.arsenal.route.telemetry
 * <p>
 * User: Vinicius
 * Date: 2/16/2026
 * Time: 1:15 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Component("telemetry")
public class TelemetryComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(
            String uri,
            String remaining,
            Map<String, Object> parameters) throws Exception {

        TelemetryEndpoint endpoint =
                new TelemetryEndpoint(uri, this);

        endpoint.setSpanName(remaining);
//        endpoint.setJourney(remaining);

        setProperties(endpoint, parameters);

        return endpoint;
    }
}

