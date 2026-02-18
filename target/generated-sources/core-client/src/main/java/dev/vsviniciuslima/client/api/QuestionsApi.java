package dev.vsviniciuslima.client.api;

import dev.vsviniciuslima.client.ApiClient;
import dev.vsviniciuslima.client.BaseApi;

import dev.vsviniciuslima.client.model.CreateCommentDTO;
import dev.vsviniciuslima.client.model.CreateQuestionDTO;
import dev.vsviniciuslima.client.model.Question;
import dev.vsviniciuslima.client.model.QuestionsPost200Response;
import java.util.UUID;

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
public class QuestionsApi extends BaseApi {

    public QuestionsApi() {
        super(new ApiClient());
    }

    public QuestionsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return Long
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Long questionsCountGet() throws RestClientException {
        return questionsCountGetWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @return ResponseEntity&lt;Long&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Long> questionsCountGetWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/questions/count", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param authorId  (optional)
     * @param correctAlternative  (optional)
     * @param disciplineId  (optional)
     * @param explanation  (optional)
     * @param id  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @param source  (optional)
     * @param sourceUrl  (optional)
     * @param statement  (optional)
     * @param topicId  (optional)
     * @param year  (optional)
     * @return List&lt;Question&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Question> questionsGet(String authorId, byte[] correctAlternative, Long disciplineId, String explanation, Long id, String namedQuery, Integer page, Integer size, List<String> sort, String source, String sourceUrl, String statement, Long topicId, Integer year) throws RestClientException {
        return questionsGetWithHttpInfo(authorId, correctAlternative, disciplineId, explanation, id, namedQuery, page, size, sort, source, sourceUrl, statement, topicId, year).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param authorId  (optional)
     * @param correctAlternative  (optional)
     * @param disciplineId  (optional)
     * @param explanation  (optional)
     * @param id  (optional)
     * @param namedQuery  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @param sort  (optional)
     * @param source  (optional)
     * @param sourceUrl  (optional)
     * @param statement  (optional)
     * @param topicId  (optional)
     * @param year  (optional)
     * @return ResponseEntity&lt;List&lt;Question&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Question>> questionsGetWithHttpInfo(String authorId, byte[] correctAlternative, Long disciplineId, String explanation, Long id, String namedQuery, Integer page, Integer size, List<String> sort, String source, String sourceUrl, String statement, Long topicId, Integer year) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "authorId", authorId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "correctAlternative", correctAlternative));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "discipline.id", disciplineId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "explanation", explanation));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "namedQuery", namedQuery));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", page));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "sort", sort));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "source", source));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sourceUrl", sourceUrl));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "statement", statement));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "topic.id", topicId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "year", year));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<List<Question>> localReturnType = new ParameterizedTypeReference<List<Question>>() {};
        return apiClient.invokeAPI("/questions", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param commentId  (required)
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void questionsIdCommentsCommentIdDelete(UUID commentId, Long id) throws RestClientException {
        questionsIdCommentsCommentIdDeleteWithHttpInfo(commentId, id);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param commentId  (required)
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> questionsIdCommentsCommentIdDeleteWithHttpInfo(UUID commentId, Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling questionsIdCommentsCommentIdDelete");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling questionsIdCommentsCommentIdDelete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("commentId", commentId);
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
        return apiClient.invokeAPI("/questions/{id}/comments/{commentId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @param createCommentDTO Comentário (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void questionsIdCommentsPost(Long id, CreateCommentDTO createCommentDTO) throws RestClientException {
        questionsIdCommentsPostWithHttpInfo(id, createCommentDTO);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @param createCommentDTO Comentário (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> questionsIdCommentsPostWithHttpInfo(Long id, CreateCommentDTO createCommentDTO) throws RestClientException {
        Object localVarPostBody = createCommentDTO;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling questionsIdCommentsPost");
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/questions/{id}/comments", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void questionsIdDelete(Long id) throws RestClientException {
        questionsIdDeleteWithHttpInfo(id);
    }

    /**
     * 
     * 
     * <p><b>204</b>
     * @param id  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> questionsIdDeleteWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling questionsIdDelete");
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
        return apiClient.invokeAPI("/questions/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return Question
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Question questionsIdGet(Long id) throws RestClientException {
        return questionsIdGetWithHttpInfo(id).getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id  (required)
     * @return ResponseEntity&lt;Question&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Question> questionsIdGetWithHttpInfo(Long id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling questionsIdGet");
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

        ParameterizedTypeReference<Question> localReturnType = new ParameterizedTypeReference<Question>() {};
        return apiClient.invokeAPI("/questions/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param question  (optional)
     * @return Question
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Question questionsIdPut(Long id, Question question) throws RestClientException {
        return questionsIdPutWithHttpInfo(id, question).getBody();
    }

    /**
     * 
     * 
     * <p><b>201</b>
     * @param id  (required)
     * @param question  (optional)
     * @return ResponseEntity&lt;Question&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Question> questionsIdPutWithHttpInfo(Long id, Question question) throws RestClientException {
        Object localVarPostBody = question;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling questionsIdPut");
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

        ParameterizedTypeReference<Question> localReturnType = new ParameterizedTypeReference<Question>() {};
        return apiClient.invokeAPI("/questions/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Criar questão
     * 
     * <p><b>200</b> - Questão criada
     * @param createQuestionDTO Lista de presentes a serem criados (optional)
     * @return QuestionsPost200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public QuestionsPost200Response questionsPost(CreateQuestionDTO createQuestionDTO) throws RestClientException {
        return questionsPostWithHttpInfo(createQuestionDTO).getBody();
    }

    /**
     * Criar questão
     * 
     * <p><b>200</b> - Questão criada
     * @param createQuestionDTO Lista de presentes a serem criados (optional)
     * @return ResponseEntity&lt;QuestionsPost200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<QuestionsPost200Response> questionsPostWithHttpInfo(CreateQuestionDTO createQuestionDTO) throws RestClientException {
        Object localVarPostBody = createQuestionDTO;
        

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

        ParameterizedTypeReference<QuestionsPost200Response> localReturnType = new ParameterizedTypeReference<QuestionsPost200Response>() {};
        return apiClient.invokeAPI("/questions", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
