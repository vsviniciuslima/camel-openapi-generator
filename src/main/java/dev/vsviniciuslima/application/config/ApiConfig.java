package dev.vsviniciuslima.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.application.config
 * <p>
 * User: Vinicius
 * Date: 2/15/2026
 * Time: 4:32 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String baseUrl;
    private String host;
    private Integer port;
    private String key;
    private String appKey;
}
