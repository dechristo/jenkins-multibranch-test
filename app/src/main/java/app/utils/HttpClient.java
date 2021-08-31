package app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient implements HttpClientInterface{

    private RestTemplate restTemplate;

    public HttpClient() {
        restTemplate = new RestTemplate();
    }

    public <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> type) {
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, type);

        return response;
    }

    public <T> ResponseEntity<T> post(String url, HttpEntity request, Class<T> type) {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, request, type);

        return response;
    }
}