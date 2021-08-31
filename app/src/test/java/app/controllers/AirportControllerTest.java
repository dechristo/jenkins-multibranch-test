package app.controllers;

import app.Config;
import app.facade.AirportDataFacade;
import app.models.Airport;
import app.services.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirportController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AirportService service;

    @MockBean
    AirportDataFacade airportDataFacade;

    @Test
    public void healthCheckShouldReturnMessageWhenAppIsRunning() throws Exception {
        this.mockMvc.perform(get("/airports/health-check"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Airport Microservice up and running...")));
    }

    @Test
    public void getAirportShouldReturnAirportAsJson() throws Exception {
        when(service.getAirport("CAN"))
            .thenReturn(
                new Airport("CAN", "IDKN", "Mexico", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun")
            );

        this.mockMvc.perform(get("/airports/CAN"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content()
                .json("{\"iataCode\":\"CAN\",\"icaoCode\":\"IDKN\",\"country\":\"Mexico\",\"region\":\"\",\"city\":\"Cancun\",\"latitude\":513213.53878855,\"longitude\":2326063.3312096,\"utcOffset\":\"-05:00\",\"timezone\":\"America/Cancun\"}"));
    }

    @Test
    public void getAirportsByCityOrCountryShould400IfBothParametersNotProvided() throws Exception {
        this.mockMvc.perform(get("/airports"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void getAirportsByCityOrCountryShouldReturnAirportsAsJsonForValidCityName() throws Exception {
        when(service.getAirportsByCityName("Cancun"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("CAN", "IDKN", "Mexico", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun"));
                    add(new Airport("CAX", "IDKN", "Mexico", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun"));
                }
            });

        this.mockMvc.perform(get("/airports?city=Cancun"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content()
                .json("[{\"iataCode\":\"CAN\",\"icaoCode\":\"IDKN\",\"country\":\"Mexico\",\"region\":\"\",\"city\":\"Cancun\",\"latitude\":513213.53878855,\"longitude\":2326063.3312096,\"utcOffset\":\"-05:00\",\"timezone\":\"America/Cancun\"},{\"iataCode\":\"CAX\",\"icaoCode\":\"IDKN\",\"country\":\"Mexico\",\"region\":\"\",\"city\":\"Cancun\",\"latitude\":513213.53878855,\"longitude\":2326063.3312096,\"utcOffset\":\"-05:00\",\"timezone\":\"America/Cancun\"}]"));
    }

    @Test
    public void getAirportsByCityOrCountryShouldReturnAirportsAsJsonForValidCityNameCaseInsensitive() throws Exception {
        when(service.getAirportsByCityName("cancun"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("CAN", "IDKN", "Mexico", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun"));
                    add(new Airport("CAX", "IDKN", "Mexico", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun"));
                }
            });

        this.mockMvc.perform(get("/airports?city=cancun"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content()
                .json("[{\"iataCode\":\"CAN\",\"icaoCode\":\"IDKN\",\"country\":\"Mexico\",\"region\":\"\",\"city\":\"Cancun\",\"latitude\":513213.53878855,\"longitude\":2326063.3312096,\"utcOffset\":\"-05:00\",\"timezone\":\"America/Cancun\"},{\"iataCode\":\"CAX\",\"icaoCode\":\"IDKN\",\"country\":\"Mexico\",\"region\":\"\",\"city\":\"Cancun\",\"latitude\":513213.53878855,\"longitude\":2326063.3312096,\"utcOffset\":\"-05:00\",\"timezone\":\"America/Cancun\"}]"));
    }

    @Test
    public void getAirportsByCityOrCountryShouldReturn404ForNonExistentAirportCity() throws Exception {
        when(service.getAirportsByCityName("cancunas"))
            .thenReturn(null);

        this.mockMvc.perform(get("/airports?city=cancun"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void getAirportShouldReturnNullIfAirportNotFound() throws Exception {
        when(service.getAirport("XA1"))
            .thenReturn(null);

        this.mockMvc.perform(get("/airports/MIA"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void enrichAirportShouldReturnSuccessMessageIfProcessingCompleted() throws Exception {
        when(service.enrich())
            .thenReturn("Not implemented yet.");

        this.mockMvc.perform(
            put("/airports")
                .header("Authorization", "Bearer " + Config.getAuthApiToken())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/plain;charset=UTF-8"))
            .andExpect(content().string("Not implemented yet."));
    }

    @Test
    public void enrichAirportShouldReturn400IfTokenNotPresent() throws Exception {
        when(service.enrich())
            .thenReturn("[ERROR]: Something wrong happened!");

        this.mockMvc.perform(put("/airports"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void enrichAirportShouldReturn403IfTokenIsIncorrect() throws Exception {
        when(service.enrich())
            .thenReturn("[ERROR]: Something wrong happened!");

        this.mockMvc.perform(
            put("/airports")
                .header("Authorization", "Bearer Wr0nGT0k3n!")
            )
            .andDo(print())
            .andExpect(status().isForbidden());
    }
}
