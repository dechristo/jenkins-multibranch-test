package app.services;

import app.Config;
import app.apiinterfaces.lufthansa.LufthansaAirportResource;
import app.apiinterfaces.lufthansa.LufthansaResource;
import app.models.Airport;
import app.repositories.AirportRepository;
import app.utils.HttpClient;
import app.utils.Logger;
import app.utils.LufthansaSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService implements AirportServiceInterface {
    private String apiUri = "/mds-references/airports/%s";
    private HttpClient httpClient;
    private LufthansaSecurity lufthansaSecurity;
    private AirportRepository repository;
    private List<Airport> airports = new ArrayList<>();

    @Autowired
    public AirportService(HttpClient httpclient,
                          LufthansaSecurity lufthansaSecurity,
                          AirportRepository repository
    ) {
        this.httpClient = httpclient;
        this.lufthansaSecurity = lufthansaSecurity;
        this.repository = repository;
    }

    public Airport getAirport(String airportCode) {
        return this.getAll()
            .stream()
            .filter(airport -> airport.getIataCode().equals(airportCode.toUpperCase()))
            .findFirst()
            .orElse(null);
    }

    public List<Airport> getAll() {
        if (this.airports.size() == 0) this.airports = this.repository.findAll();

        return this.airports;
    }

    public List<Airport> getAirportsByCityName(String cityName) {
        return this.repository.findByCityIgnoreCase(cityName);
    }

    public List<Airport> getAirportsByCountryName(String country) {
        return this.repository.findByCountryIgnoreCase(country);
    }

    public String enrich() {
        return "Not implemented yet.";
    }

    private LufthansaAirportResource getDataFromLufthansaApi(String airportCode) {
        Logger.info("Triggering GET: " + Config.getLufthansaApiBaseUrl() + String.format(apiUri, airportCode));
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.setBearerAuth(lufthansaSecurity.getToken());
            ResponseEntity<LufthansaResource> response = httpClient
                .get(Config.getLufthansaApiBaseUrl() + String.format(apiUri, airportCode),
                    headers,
                    LufthansaResource.class);
            return response.getBody().getAirportResource();
        } catch (Exception ex) {
            Logger.error(ex.getMessage());
            return null;
        }
    }

    private void waitForNextRequest(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ex) {
            Logger.error("Wait for next request failed:" + ex.getMessage());
        }
    }
}
