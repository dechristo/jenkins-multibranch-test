package app.utils;

import app.Config;
import app.apiinterfaces.lufthansa.LufthansaToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LufthansaSecurityTest {
    private HttpEntity postRequest;
    @Mock
    HttpClient httpClient;

    @InjectMocks
    LufthansaSecurity lufthansaSecurity = new LufthansaSecurity();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", Config.getLufthansaClientId());
        map.add("client_secret", Config.getLufthansaClientSecret());
        map.add("grant_type", Config.getLufthansaGrantType());

        postRequest = new HttpEntity<>(map, headers);
    }

    @Test
    public void getTokenReturnsTokenSetInPostConstruct() {
        String tokenUrl = Config.getLufthansaApiBaseUrl() + "/oauth/token";

        when(httpClient.post(tokenUrl, postRequest, LufthansaToken.class))
            .thenReturn(new ResponseEntity<>(new LufthansaToken("a1b3c3e9x"), HttpStatus.OK));

        lufthansaSecurity.Initialize();
        assertEquals("a1b3c3e9x",lufthansaSecurity.getToken());
        verify(httpClient, only())
            .post(tokenUrl, postRequest, LufthansaToken.class);
    }

    @Test
    public void getTokenReturnsTokenSetInsideGetTokenWhenTokenIsEmptyConstruct() {
        HttpHeaders fakeHeaders = new HttpHeaders();
        String tokenUrl = Config.getLufthansaApiBaseUrl() + "/oauth/token";

        when(httpClient.post(tokenUrl, postRequest, LufthansaToken.class))
            .thenReturn(new ResponseEntity<>(new LufthansaToken("a1b3c3e9x"), HttpStatus.OK));

        assertEquals("a1b3c3e9x",lufthansaSecurity.getToken());
        verify(httpClient, only())
            .post(tokenUrl, postRequest, LufthansaToken.class);
    }
}
