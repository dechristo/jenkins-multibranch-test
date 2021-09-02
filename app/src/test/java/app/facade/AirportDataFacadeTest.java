package app.facade;

import app.Config;
import app.models.Airport;
import app.repositories.AirportRepository;
import app.utils.CsvManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirportDataFacadeTest {

    @Mock
    private CsvManager mockCsvManager;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    AirportDataFacade airportDataFacade = new AirportDataFacade();

    @BeforeEach
    public void setup() {
        mockCsvManager = mock(CsvManager.class);
        airportRepository = mock(AirportRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void InitialzeDoesNotTriggersEnrichmentIfDataAlreadyExistsInDb() throws Exception {
        when(airportRepository.findAll())
            .thenReturn(new ArrayList<Airport>() {
                {
                    add(new Airport());
                }
            });

        airportDataFacade.Initialize();

        verify(airportRepository, only())
            .findAll();

        verify(mockCsvManager, never())
            .readAll(anyString(), anyBoolean());
    }

    @Test
    public void InitializeTriggersEnrichmentAndUpdateDbIfTableIsEmpty() throws Exception {

        when(airportRepository.findAll())
            .thenReturn(new ArrayList<Airport>());

        when(mockCsvManager.readAll(Config.getOpenApiAirportsCsv(), false))
            .thenReturn(new ArrayList<String[]>() {
                {
                    add(new String[]{"3576","Miami State Airport","Miami","United States","","KMIA","25.9953918457","-77.29060363769531","8","-5","A","America/New_York","airport","OurAirports"});
                    add(new String[] {"13648","West Melton Aerodrome","West Melton","New Zealand","N","NZWL","-43.47669982910156","172.39700317382812","312","12","Z","N","airport","OurAirports"});
                }
            });

        when(mockCsvManager.readAll(Config.getAirportsCsv(), false))
            .thenReturn(new ArrayList<String[]>() {
                {
                    add(new String[]{"US","MIA","Miami International Airport","Miami","FL"});
                    add(new String[]{"US","MIB","Minot AFB Airport","Minot","ND"});
                }
            });

        when(mockCsvManager.readAll(Config.getCountriesCsv(), false))
            .thenReturn(new ArrayList<String[]>() {
                {
                    add(new String[]{"EN","US","USA","American","UNITED STATES OF AMERICA","American"});
                }
            });

        airportDataFacade.Initialize();

        verify(airportRepository, times(1))
            .findAll();

        verify(mockCsvManager, times(3))
            .readAll(anyString(), anyBoolean());

        verify(airportRepository, times(2))
            .save(any(Airport.class));
    }

    // Todo: Add more unit tests, e.g.: when IATA Code is "" or N. Also with other cases used in the .filter()
}
