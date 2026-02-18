package dev.vsviniciuslima.arsenal.exchange.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.application.config.interceptor
 * <p>#
 * User: Vinicius
 * Date: 2/16/2026
 * Time: 11:58 AM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BearerTokenInterceptor implements ClientHttpRequestInterceptor {
    private static final ThreadLocal<String> TOKEN_HOLDER = new ThreadLocal<>();

    public static void setToken(String token) {
        TOKEN_HOLDER.set(token.startsWith("Bearer ") ? token : "Bearer " + token);
    }

    public static void clearToken() {
        TOKEN_HOLDER.remove();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution
    ) throws IOException {
        String token = TOKEN_HOLDER.get();

        if (token != null) {
            request.getHeaders().set("Authorization", token);
        }

        return execution.execute(request, body);
    }
}
