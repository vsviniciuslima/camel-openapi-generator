package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.api.PetsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PetsApiConfig {

    @Value("${api.base-url}")
    private String baseUrl;

    private final CustomApiClient customApiClient;

    @Bean
    public PetsApi petsApi() {
        customApiClient.setBasePath(baseUrl);

        return new PetsApi(customApiClient);
    }

}
