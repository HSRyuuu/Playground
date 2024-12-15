package com.example.playground.spring.restapi;

import com.example.playground.spring.restapi.request.ExtraHeaders;
import com.example.playground.spring.restapi.request.QueryParams;
import com.example.playground.spring.restapi.response.ApiResponse;
import com.example.playground.spring.restapi.util.RestApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * execute HTTP GET Request
     *
     * @param url          : base url
     * @param queryParams  : query parameters
     * @param extraHeaders : Http Headers
     * @param data         : Http Body (optional)
     * @return ApiResponse(status, body, errorMessage)
     */
    public ApiResponse get(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.GET, url, queryParams, extraHeaders, data);
    }

    public ApiResponse get(String url) {
        return this.get(url, null, null, null);
    }

    public ApiResponse get(String url, ExtraHeaders extraHeaders) {
        return this.get(url, null, extraHeaders, null);
    }

    // POST
    public ApiResponse post(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.POST, url, queryParams, extraHeaders, data);
    }

    public ApiResponse post(String url, Object body) {
        return this.post(url, null, null, body);
    }

    public ApiResponse post(String url, ExtraHeaders extraHeaders, Object body) {
        return this.post(url, null, extraHeaders, body);
    }

    // PUT
    public ApiResponse put(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.PUT, url, queryParams, extraHeaders, data);
    }

    public ApiResponse put(String url, Object body) {
        return this.put(url, null, null, body);
    }

    public ApiResponse put(String url, ExtraHeaders extraHeaders, Object body) {
        return this.put(url, null, extraHeaders, body);
    }

    // DELETE
    public ApiResponse delete(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.DELETE, url, queryParams, extraHeaders, data);
    }

    public ApiResponse delete(String url, Object body) {
        return this.delete(url, null, null, null);
    }

    public ApiResponse delete(String url, ExtraHeaders extraHeaders) {
        return this.delete(url, null, extraHeaders, null);
    }

    /**
     * HTTP Request
     *
     * @param httpMethod      GET / POST / PUT / DELETE
     * @param baseUrl         Request URL
     * @param queryParameters query parameter map
     * @param extraHeaders    http header map
     * @param body            Request Body
     * @return ApiResponse(status, body, errorMessage)
     */
    private ApiResponse getResult(HttpMethod httpMethod,
                                  String baseUrl,
                                  QueryParams queryParameters,
                                  ExtraHeaders extraHeaders,
                                  Object body) {
        //baseUrl validation
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Base URL cannot be null or empty value");
        }
        try {
            String url = RestApiUtils.buildFullUrl(baseUrl, queryParameters);
            HttpHeaders headers = RestApiUtils.generateHttpHeaders(extraHeaders);

            log.info("[{}] API Request: URL=[{}], Headers=[{}], Body=[{}]", httpMethod, url, headers, body);
            long startTime = System.currentTimeMillis();

            HttpEntity<Object> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request, String.class);

            log.info("[{}] API Response: Status=[{}], TimeElapsed={}ms, Body=[{}]",
                    httpMethod, response.getStatusCode(), System.currentTimeMillis() - startTime, response.getBody());
            return new ApiResponse(response.getStatusCode(), null, response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ApiResponse(e.getStatusCode(), e.getResponseBodyAsString(), e.getMessage());
        } catch (RestClientException e) {
            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.unexpectedError();
        }
    }

}
