package dev.vsviniciuslima.arsenal.exchange.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

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
public class AppKeyQueryParamInterceptor implements ClientHttpRequestInterceptor {

    @Value("${api.app-key}")
    private String appKey;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI requestURI = request.getURI();
        
        return requestURI.toString().contains("&gw-app-key") || requestURI.toString().contains("?gw-app-key")
                ? execution.execute(request, body)
                : execution.execute(new HttpRequestWrapper(request) {
                    @Override
                    public URI getURI() {
                        return UriComponentsBuilder.fromUri(requestURI)
                                .queryParam("gw-app-key", appKey)
                                .build()
                                .toUri();
                    }
                }, body);
    }
}
