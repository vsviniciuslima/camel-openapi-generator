package dev.vsviniciuslima.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

@Configuration
public class CamelConfig {

    @Value("classpath:specs/third-party-api.yaml")
    private Resource openapiSpec;

    @Value("${third.party.api.host:localhost}")
    private String apiHost;

    @Value("${third.party.api.port:8080}")
    private String apiPort;

    @Bean
    public RouteBuilder openApiRouteConfig() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String specContent = IOUtils.toString(openapiSpec.getInputStream(), StandardCharsets.UTF_8);

                restConfiguration()
                        .component("netty-http")
                        .host(apiHost)
                        .port(apiPort)
                        .bindingMode(RestBindingMode.json)
                        .apiProperty("openapi.specification", specContent)
                        .clientRequestValidation(true);
            }
        };
    }
}