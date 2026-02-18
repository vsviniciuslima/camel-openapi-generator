package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.arsenal.exchange.interceptor.ApiKeyHeaderInterceptor;
import dev.vsviniciuslima.client.ApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SantanderApiClient extends ApiClient {
    private final ApiKeyHeaderInterceptor apiKeyInterceptor;

    @Override
    protected RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = super.buildRestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add(apiKeyInterceptor);
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

}
