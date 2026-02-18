package dev.vsviniciuslima.application.config;

import dev.vsviniciuslima.client.api.PetsApi;
import dev.vsviniciuslima.client.model.CreatePet;
import dev.vsviniciuslima.client.model.PetsPost200Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

/**
 * Project: camel-openapi-generator
 * Package: dev.vsviniciuslima.application.config
 * <p>
 * User: Vinicius
 * Date: 2/16/2026
 * Time: 12:26 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
public class PetsApiImpl extends PetsApi {
    @Override
    public PetsPost200Response petsPost(Boolean fail, CreatePet createPet) throws RestClientException {
        String url = this.apiClient.getBasePath() + "/pets";
//        this.getApiClient().invo
        invokeAPI(url, HttpMethod.POST, createPet, ParameterizedTypeReference.<PetsPost200Response>forType(PetsPost200Response.class));
        return petsPostWithHttpInfo(fail, createPet).getBody();
    }
}
