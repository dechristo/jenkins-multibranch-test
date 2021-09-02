package app.services;

import app.models.Airport;
import app.repositories.AirportRepository;
import app.utils.HttpClient;
import app.utils.LufthansaSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirportServiceTest {

    private HttpClient mockHttpClient;
    private LufthansaSecurity mockLufthansaSecurity;
    private AirportRepository mockRepository;


    @BeforeEach
    public void setup() {
        mockHttpClient = mock(HttpClient.class);
        mockLufthansaSecurity = mock(LufthansaSecurity.class);
        mockRepository = mock(AirportRepository.class);
     }

    @Test
    public void getAirportReturnsCorrectMappedAirport() {
        when(mockRepository.findAll())
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","","","",0.0,0.0,"",""));
                    add(new Airport("FK2", "","","","",0.0,0.0,"",""));
                    add(new Airport("CAN", "IDKN", "MX", "", "Cancun", 513213.53878855, 2326063.3312096, "-05:00", "America/Cancun"));
                    add(new Airport("FK3", "","","","",0.0,0.0,"",""));

                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        Airport airport = service.getAirport("CAN");

        assertNotNull(airport);
        assertEquals("Cancun", airport.getCity());
        assertEquals("CAN", airport.getIataCode());
        assertEquals("MX", airport.getCountry());
        assertEquals("", airport.getRegion());
        assertEquals("Cancun", airport.getCity());
        assertEquals(513213.53878855, airport.getLatitude());
        assertEquals(2326063.3312096, airport.getLongitude());
        assertEquals("-05:00",airport.getUtcOffset());
        assertEquals("America/Cancun", airport.getTimezone());
    }

    @Test
    public void getAirportReturnsNullIfNoAirportFound() {
        when(mockRepository.findAll())
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","","","",0.0,0.0,"",""));
                    add(new Airport("FK2", "","","","",0.0,0.0,"",""));
                    add(new Airport("FK3", "","","","",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        Airport airport = service.getAirport("KIN");

        assertNull(airport);
    }

    @Test
    public void  getAirportsByCityNameReturnsListOfAirportsWithCaseSentive() {
        when(mockRepository.findByCityIgnoreCase("Miami"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","","","Miami",0.0,0.0,"",""));
                    add(new Airport("FK2", "","","","Miami",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAirportsByCityName("Miami");

        assertEquals(2, result.size());
        assertEquals("Miami",result.get(0).getCity());
        assertEquals("Miami",result.get(1).getCity());
    }

    @Test
    public void  getAirportsByCityNameReturnsListOfAirportsWithCaseInsensitive() {
        when(mockRepository.findByCityIgnoreCase("miami"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","United States of America","","Miami",0.0,0.0,"",""));
                    add(new Airport("FK2", "","United States of America","","Miami",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAirportsByCityName("miami");

        assertEquals(2, result.size());
        assertEquals("Miami",result.get(0).getCity());
        assertEquals("Miami",result.get(1).getCity());
    }

    @Test
    public void  getAirportsByCountryNameReturnsListOfAirportsWithCaseSentive() {
        when(mockRepository.findByCountryIgnoreCase("Germany"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FK3", "","Germany","","Berlin",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAirportsByCountryName("Germany");

        assertEquals(1, result.size());
        assertEquals("Germany",result.get(0).getCountry());
    }

    @Test
    public void  getAirportsByCountryNameReturnsListOfAirportsWithCaseInsensitive() {
        when(mockRepository.findByCountryIgnoreCase("United States of America"))
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","United States of America","","Miami",0.0,0.0,"",""));
                    add(new Airport("FK2", "","United States of America","","Miami",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAirportsByCountryName("United States of America");

        assertEquals(2, result.size());
        assertEquals("United States of America",result.get(0).getCountry());
        assertEquals("United States of America",result.get(1).getCountry());
    }

    @Test
    public void getAllReturnsAllAirportsAsList() {
        when(mockRepository.findAll())
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport("FKE", "","United States of America","","Miami",0.0,0.0,"",""));
                    add(new Airport("FK2", "","United States of America","","Miami",0.0,0.0,"",""));
                    add(new Airport("FK3", "","Germany","","Berlin",0.0,0.0,"",""));
                    add(new Airport("FK3", "","Norway","","Oslo",0.0,0.0,"",""));
                    add(new Airport("FK3", "","Mexico","","Cancun",0.0,0.0,"",""));
                    add(new Airport("FK3", "","Finland","","Lahti",0.0,0.0,"",""));
                }
            });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAll();

        assertEquals(6, result.size());
        verify(this.mockRepository, times(1))
            .findAll();
    }

    @Test
    public void getAllReturnsAllAirportsAsListAndAfterFirstCallGetsDataFromMemoryInsteadOfDatabase() {
        when(mockRepository.findAll())
                .thenReturn(new ArrayList<Airport>() {
                    {
                        add(new Airport("FKE", "","United States of America","","Miami",0.0,0.0,"",""));
                        add(new Airport("FK2", "","United States of America","","Miami",0.0,0.0,"",""));
                        add(new Airport("FK3", "","Germany","","Berlin",0.0,0.0,"",""));
                        add(new Airport("FK3", "","Norway","","Oslo",0.0,0.0,"",""));
                        add(new Airport("FK3", "","Mexico","","Cancun",0.0,0.0,"",""));
                        add(new Airport("FK3", "","Finland","","Lahti",0.0,0.0,"",""));
                    }
                });

        AirportService service = new AirportService(mockHttpClient, mockLufthansaSecurity, mockRepository);
        List<Airport> result = service.getAll();
        service.getAll();
        service.getAll();
        assertEquals(6, result.size());
        verify(this.mockRepository, times(1))
            .findAll();
    }
}