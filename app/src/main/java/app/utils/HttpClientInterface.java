package app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface HttpClientInterface {
    <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> type);
    <T> ResponseEntity<T> post(String url, HttpEntity entity, Class<T> type);
}
