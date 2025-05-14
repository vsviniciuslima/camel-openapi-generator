package dev.vsviniciuslima.application.config;


import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Bean
    public DataFormat getDataFormat() {
        return new JacksonDataFormat();
    }
}
