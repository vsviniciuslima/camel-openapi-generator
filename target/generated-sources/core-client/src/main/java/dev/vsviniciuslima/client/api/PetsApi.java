package dev.vsviniciuslima.client.api;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.BaseApi;

import dev.vsviniciuslima.client.model.CreatePet;
import dev.vsviniciuslima.client.model.ErrorsIdGet200Response;
import dev.vsviniciuslima.client.model.PetsPost200Response;
import dev.vsviniciuslima.client.model.PetsPost400Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class PetsApi extends BaseApi {

    public PetsApi() {
        super(new ApiClient());
    }

    public PetsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Consultar detalhes do erro
     * 
     * <p><b>200</b> - Detalhes do erro
     * @param id  (required)
     * @return ErrorsIdGet200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ErrorsIdGet200Response errorsIdGet(String id) throws RestClientException {
        return errorsIdGetWithHttpInfo(id).getBody();
    }

    /**
     * Consultar detalhes do erro
     * 
     * <p><b>200</b> - Detalhes do erro
     * @param id  (required)
     * @return ResponseEntity&lt;ErrorsIdGet200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ErrorsIdGet200Response> errorsIdGetWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling errorsIdGet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "api_key" };

        ParameterizedTypeReference<ErrorsIdGet200Response> localReturnType = new ParameterizedTypeReference<ErrorsIdGet200Response>() {};
        return apiClient.invokeAPI("/errors/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cadastrar pet
     * 
     * <p><b>200</b> - Pet cadastrado
     * <p><b>400</b> - Erro no cadastro
     * @param fail  (optional)
     * @param createPet Simulado a ser criado (optional)
     * @return PetsPost200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PetsPost200Response petsPost(Boolean fail, CreatePet createPet) throws RestClientException {
        return petsPostWithHttpInfo(fail, createPet).getBody();
    }

    /**
     * Cadastrar pet
     * 
     * <p><b>200</b> - Pet cadastrado
     * <p><b>400</b> - Erro no cadastro
     * @param fail  (optional)
     * @param createPet Simulado a ser criado (optional)
     * @return ResponseEntity&lt;PetsPost200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PetsPost200Response> petsPostWithHttpInfo(Boolean fail, CreatePet createPet) throws RestClientException {
        Object localVarPostBody = createPet;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fail", fail));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<PetsPost200Response> localReturnType = new ParameterizedTypeReference<PetsPost200Response>() {};
        return apiClient.invokeAPI("/pets", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
