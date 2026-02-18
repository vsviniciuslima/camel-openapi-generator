package dev.vsviniciuslima.client.api;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.BaseApi;

import dev.vsviniciuslima.client.model.AddExamQuestion;
import dev.vsviniciuslima.client.model.CreateExamDTO;
import dev.vsviniciuslima.client.model.Exam;
import dev.vsviniciuslima.client.model.ExamsPost200Response;

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
public class ExamsApi extends BaseApi {

    public ExamsApi() {
        super(new ApiClient());
    }

    public ExamsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return Long
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Long examsCountGet() throws RestClientException {
        return examsCountGetWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return ResponseEntity&lt;Long&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Long> examsCountGetWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/exams/count", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param examId  (required)
     * @param addExamQuestion  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void examsExamIdQuestionsPost(Long examId, AddExamQuestion addExamQuestion) throws RestClientException {
        examsExamIdQuestionsPostWithHttpInfo(examId, addExamQuestion);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param examId  (required)
     * @param addExamQuestion  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> examsExamIdQuestionsPostWithHttpInfo(Long examId, AddExamQuestion addExamQuestion) throws RestClientException {
        Object localVarPostBody = addExamQuestion;
        
        // verify the required parameter 'examId' is set
        if (examId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'examId' when calling examsExamIdQuestionsPost");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("examId", examId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/exams/{examId}/questions", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param active  (optional)
     * @param author  (optional)
     * @param authorId  (optional)
     * @param description  (optional)
     * @param disciplineId  (optional)
     * @param id  (optional)
     * @param imageUrl  (optional)
     * @param name  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @param topicId  (optional)
     * @param version  (optional)
     * @param year  (optional)
     * @return List&lt;Exam&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Exam> examsGet(Boolean active, String author, String authorId, String description, Long disciplineId, Long id, String imageUrl, String name, String namedQuery, Integer page, Integer size, List<String> sort, Long topicId, Integer version, Integer year) throws RestClientException {
        return examsGetWithHttpInfo(active, author, authorId, description, disciplineId, id, imageUrl, name, namedQuery, page, size, sort, topicId, version, year).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param active  (optional)
     * @param author  (optional)
     * @param authorId  (optional)
     * @param description  (optional)
     * @param disciplineId  (optional)
     * @param id  (optional)
     * @param imageUrl  (optional)
     * @param name  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @param topicId  (optional)
     * @param version  (optional)
     * @param year  (optional)
     * @return ResponseEntity&lt;List&lt;Exam&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Exam>> examsGetWithHttpInfo(Boolean active, String author, String authorId, String description, Long disciplineId, Long id, String imageUrl, String name, String namedQuery, Integer page, Integer size, List<String> sort, Long topicId, Integer version, Integer year) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "author", author));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "authorId", authorId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "description", description));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "discipline.id", disciplineId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "imageUrl", imageUrl));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "namedQuery", namedQuery));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "sort", sort));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "topic.id", topicId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "year", year));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<List<Exam>> localReturnType = new ParameterizedTypeReference<List<Exam>>() {};
        return apiClient.invokeAPI("/exams", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void examsIdDelete(Long id) throws RestClientException {
        examsIdDeleteWithHttpInfo(id);
    }

    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> examsIdDeleteWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling examsIdDelete");
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
        return apiClient.invokeAPI("/exams/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return Exam
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Exam examsIdGet(Long id) throws RestClientException {
        return examsIdGetWithHttpInfo(id).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return ResponseEntity&lt;Exam&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Exam> examsIdGetWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling examsIdGet");
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

        ParameterizedTypeReference<Exam> localReturnType = new ParameterizedTypeReference<Exam>() {};
        return apiClient.invokeAPI("/exams/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param exam  (optional)
     * @return Exam
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Exam examsIdPut(Long id, Exam exam) throws RestClientException {
        return examsIdPutWithHttpInfo(id, exam).getBody();
    }

    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param exam  (optional)
     * @return ResponseEntity&lt;Exam&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Exam> examsIdPutWithHttpInfo(Long id, Exam exam) throws RestClientException {
        Object localVarPostBody = exam;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling examsIdPut");
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

        ParameterizedTypeReference<Exam> localReturnType = new ParameterizedTypeReference<Exam>() {};
        return apiClient.invokeAPI("/exams/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Criar simulado
     * 
     * <p><b>200</b> - Simulado criado
     * @param createExamDTO Simulado a ser criado (optional)
     * @return ExamsPost200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExamsPost200Response examsPost(CreateExamDTO createExamDTO) throws RestClientException {
        return examsPostWithHttpInfo(createExamDTO).getBody();
    }

    /**
     * Criar simulado
     * 
     * <p><b>200</b> - Simulado criado
     * @param createExamDTO Simulado a ser criado (optional)
     * @return ResponseEntity&lt;ExamsPost200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExamsPost200Response> examsPostWithHttpInfo(CreateExamDTO createExamDTO) throws RestClientException {
        Object localVarPostBody = createExamDTO;
        

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

        ParameterizedTypeReference<ExamsPost200Response> localReturnType = new ParameterizedTypeReference<ExamsPost200Response>() {};
        return apiClient.invokeAPI("/exams", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
