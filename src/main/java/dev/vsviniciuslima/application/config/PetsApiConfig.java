package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.arsenal.exchange.interceptor.ApiKeyHeaderInterceptor;
import dev.vsviniciuslima.client.api.PetsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PetsApiConfig {
    private final ApiKeyHeaderInterceptor apiKeyInterceptor;
    private final SantanderApiClient apiClient;

    @Bean
    public PetsApi petsApi(ApiConfig apiConfig) {
        apiClient.setBasePath(apiConfig.getBaseUrl());

        // Adiciona a API key como um header padrão para todas as requisições
        apiClient.addDefaultHeader("api-key", apiConfig.getKey());

        return new PetsApi(apiClient);
    }

    private void addApiKey(String apiKey) {
        var authentications = apiClient.getAuthentications();
        authentications.put("api-key", (queryParams, headerParams, cookieParams) ->
                headerParams.put("api-key", java.util.Collections.singletonList(apiKey))
        );
    }

}
