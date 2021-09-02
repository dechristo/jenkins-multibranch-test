package app.controllers;

import app.Config;
import app.models.Airport;
import app.services.AirportService;
import app.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/airports/health-check")
    public String healthCheck() {
        return "Airport Microservice up and running...";
    }

    @Cacheable(value = "airports", key = "#airportCode")
    @GetMapping("/airports/{airportCode}")
    public Airport getAirport(@PathVariable String airportCode) {
        Logger.info("GET /airports/" + airportCode);
        return buildResponse(airportService.getAirport(airportCode));
    }

    @Cacheable(value = "airports", key = "{ #root.methodName, #city, #country }")
    @GetMapping("/airports")
    public List<Airport> getAirportsByCityOrCountry(
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String country) {

        if (bothCityAndCountryNotProvided(city, country)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if (city != null) {
            Logger.info("GET /airports?city=" + city);
            return buildResponse(airportService.getAirportsByCityName(city));
        }

        Logger.info("GET /airports?country=" + country);
        return buildResponse(airportService.getAirportsByCountryName(country));
    }

    @PutMapping("/airports")
    public ResponseEntity enrichAirports(@RequestHeader("Authorization") String authHeader) {
        if (!authHeader.equals("Bearer " + Config.getAuthApiToken())) return new ResponseEntity(HttpStatus.FORBIDDEN);

        return new ResponseEntity("Not implemented yet.", HttpStatus.OK);
    }

    private List<Airport> buildResponse(List<Airport> airports) {
        if (airports.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return airports;
    }

    private Airport buildResponse(Airport airport) {
        if (airport == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return airport;
    }

    private boolean bothCityAndCountryNotProvided(String city, String country) {
        return (city == null || city.isEmpty()) &&
            (country == null || country.isEmpty());
    }
}