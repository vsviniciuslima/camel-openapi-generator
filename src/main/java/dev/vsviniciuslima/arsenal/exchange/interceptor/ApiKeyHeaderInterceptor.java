package dev.vsviniciuslima.arsenal.exchange.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.application.config
 * <p>
 * User: Vinicius
 * Date: 2/15/2026
 * Time: 7:08 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ApiKeyHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Value("${api.key}")
    private String apiKey;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(!request.getHeaders().containsKey("api-key")) {
            request.getHeaders().put("api-key", java.util.Collections.singletonList(apiKey));
        }

        return execution.execute(request, body);
    }
}
