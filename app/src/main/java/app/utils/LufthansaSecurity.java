package app.utils;

import app.Config;
import app.apiinterfaces.lufthansa.LufthansaToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;

@Component
public class LufthansaSecurity {
    private String tokenUri = "/oauth/token";
    private String token = "";
    private final String CLIENT_ID = "client_id";
    private final String CLIENT_SECRET = "client_secret";
    private final String GRANT_TYPE = "grant_type";

    @Autowired
    private HttpClient httpClient;

    public LufthansaSecurity() { }

    @PostConstruct
    public void Initialize() {
        this.getNewToken();
    }

    public String getToken() {
        if (this.token.isEmpty()){
            return this.getNewToken();
        }

        return this.token;
    }

    private String getNewToken () {
        ResponseEntity<LufthansaToken> response = httpClient
            .post(Config.getLufthansaApiBaseUrl() + tokenUri, this.buildHttpEntity(), LufthansaToken.class);

        this.token = response.getBody().getAccessToken();
        return this.token;
    }

    private HttpEntity buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(CLIENT_ID, Config.getLufthansaClientId());
        map.add(CLIENT_SECRET, Config.getLufthansaClientSecret());
        map.add(GRANT_TYPE, Config.getLufthansaGrantType());

        return new HttpEntity<>(map, headers);
    }
}
