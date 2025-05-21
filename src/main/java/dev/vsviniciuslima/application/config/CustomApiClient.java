package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.client.ApiClient;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomApiClient extends ApiClient {

    @Override
    protected RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = super.buildRestTemplate();

        // Add custom interceptors
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add((request, body, execution) -> {
            // Example: Add a custom header
            request.getHeaders().add("X-Custom-Header", "CustomValue");

            try {
                return execution.execute(request, body);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        });
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
