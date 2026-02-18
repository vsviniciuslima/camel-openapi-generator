package dev.vsviniciuslima.client.api;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.BaseApi;

import dev.vsviniciuslima.client.model.CreateDiscipline;
import dev.vsviniciuslima.client.model.Discipline;
import dev.vsviniciuslima.client.model.DisciplinesPost200Response;

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
public class DisciplinesApi extends BaseApi {

    public DisciplinesApi() {
        super(new ApiClient());
    }

    public DisciplinesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return Long
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Long disciplinesCountGet() throws RestClientException {
        return disciplinesCountGetWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return ResponseEntity&lt;Long&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Long> disciplinesCountGetWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Long> localReturnType = new ParameterizedTypeReference<Long>() {};
        return apiClient.invokeAPI("/disciplines/count", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param active  (optional)
     * @param id  (optional)
     * @param name  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @return List&lt;Discipline&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Discipline> disciplinesGet(Boolean active, Long id, String name, String namedQuery, Integer page, Integer size, List<String> sort) throws RestClientException {
        return disciplinesGetWithHttpInfo(active, id, name, namedQuery, page, size, sort).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param active  (optional)
     * @param id  (optional)
     * @param name  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @return ResponseEntity&lt;List&lt;Discipline&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Discipline>> disciplinesGetWithHttpInfo(Boolean active, Long id, String name, String namedQuery, Integer page, Integer size, List<String> sort) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "namedQuery", namedQuery));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "sort", sort));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<List<Discipline>> localReturnType = new ParameterizedTypeReference<List<Discipline>>() {};
        return apiClient.invokeAPI("/disciplines", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void disciplinesIdDelete(Long id) throws RestClientException {
        disciplinesIdDeleteWithHttpInfo(id);
    }

    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> disciplinesIdDeleteWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling disciplinesIdDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/disciplines/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return Discipline
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Discipline disciplinesIdGet(Long id) throws RestClientException {
        return disciplinesIdGetWithHttpInfo(id).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return ResponseEntity&lt;Discipline&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Discipline> disciplinesIdGetWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling disciplinesIdGet");
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

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Discipline> localReturnType = new ParameterizedTypeReference<Discipline>() {};
        return apiClient.invokeAPI("/disciplines/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param discipline  (optional)
     * @return Discipline
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Discipline disciplinesIdPut(Long id, Discipline discipline) throws RestClientException {
        return disciplinesIdPutWithHttpInfo(id, discipline).getBody();
    }

    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param discipline  (optional)
     * @return ResponseEntity&lt;Discipline&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Discipline> disciplinesIdPutWithHttpInfo(Long id, Discipline discipline) throws RestClientException {
        Object localVarPostBody = discipline;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling disciplinesIdPut");
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Discipline> localReturnType = new ParameterizedTypeReference<Discipline>() {};
        return apiClient.invokeAPI("/disciplines/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Criar disciplina
     * 
     * <p><b>200</b> - Disciplina criada
     * @param createDiscipline Simulado a ser criado (optional)
     * @return DisciplinesPost200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DisciplinesPost200Response disciplinesPost(CreateDiscipline createDiscipline) throws RestClientException {
        return disciplinesPostWithHttpInfo(createDiscipline).getBody();
    }

    /**
     * Criar disciplina
     * 
     * <p><b>200</b> - Disciplina criada
     * @param createDiscipline Simulado a ser criado (optional)
     * @return ResponseEntity&lt;DisciplinesPost200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DisciplinesPost200Response> disciplinesPostWithHttpInfo(CreateDiscipline createDiscipline) throws RestClientException {
        Object localVarPostBody = createDiscipline;
        

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

        ParameterizedTypeReference<DisciplinesPost200Response> localReturnType = new ParameterizedTypeReference<DisciplinesPost200Response>() {};
        return apiClient.invokeAPI("/disciplines", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
