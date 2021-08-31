package app.utils;

import app.apiinterfaces.lufthansa.LufthansaAirportResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    HttpClient httpClient = new HttpClient();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRequestShouldCallExchangeOneTime() {
        HttpHeaders fakeHeaders = new HttpHeaders();
        HttpEntity<String> fakeRequest = new HttpEntity<String>("body", fakeHeaders);
        String fakeUrl = "https://www.flightcompare.com/some/fake/url";

        when(restTemplate.exchange(fakeUrl, HttpMethod.GET, fakeRequest, LufthansaAirportResource.class))
            .thenReturn(new ResponseEntity<>(new LufthansaAirportResource(), HttpStatus.OK));

        httpClient.get(fakeUrl, fakeHeaders, LufthansaAirportResource.class);

        verify(restTemplate, only())
            .exchange(fakeUrl, HttpMethod.GET, fakeRequest, LufthansaAirportResource.class);
    }

    @Test
    public void postRequestShouldCallExchangeOneTime() {
        HttpHeaders fakeHeaders = new HttpHeaders();
        HttpEntity<String> fakeRequest = new HttpEntity<String>("body", fakeHeaders);
        String fakeUrl = "https://www.flightcompare.com/some/fake/url";

        when(restTemplate.exchange(fakeUrl, HttpMethod.POST, fakeRequest, LufthansaAirportResource.class))
            .thenReturn(new ResponseEntity<>(new LufthansaAirportResource(), HttpStatus.OK));

        httpClient.post(fakeUrl, fakeRequest, LufthansaAirportResource.class);

        verify(restTemplate, only())
            .exchange(fakeUrl, HttpMethod.POST, fakeRequest, LufthansaAirportResource.class);
    }
}