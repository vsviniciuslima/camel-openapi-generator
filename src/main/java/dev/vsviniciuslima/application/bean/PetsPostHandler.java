package dev.vsviniciuslima.application.bean;

import dev.vsviniciuslima.arsenal.exchange.interceptor.BearerTokenInterceptor;
import dev.vsviniciuslima.client.api.PetsApi;
import dev.vsviniciuslima.client.auth.Authentication;
import dev.vsviniciuslima.client.model.CreatePet;
import dev.vsviniciuslima.client.model.PetsPost200Response;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.application.bean
 * <p>
 * User: Vinicius
 * Date: 2/15/2026
 * Time: 5:36 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class PetsPostHandler {
    private final PetsApi petsApi;

    @Handler
    public PetsPost200Response handle(@ExchangeProperty("name") String name, @Header("fail") boolean fail, @Header("Authorization") String authorization) {
        Map<String, Authentication> authentications = petsApi.getApiClient().getAuthentications();
        authentications.put("Bearer", (queryParams, headerParams, cookieParams) ->
                headerParams.put("Authorization", java.util.Collections.singletonList(authorization))
        );

        BearerTokenInterceptor.setToken(authorization);

        return petsApi.petsPost(fail, CreatePet.builder()
                .name(name)
                .build());
    }



}
