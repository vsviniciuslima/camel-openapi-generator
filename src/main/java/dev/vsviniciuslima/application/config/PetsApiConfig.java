package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.api.PetsApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetsApiConfig {

    @Value("${api.base-url}")
    private String baseUrl;

    @Bean
    public PetsApi petsApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(baseUrl);

        return new PetsApi(apiClient);
    }

}
