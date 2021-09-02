package app.facade;

import app.Config;
import app.models.Airport;
import app.repositories.AirportRepository;
import app.utils.CsvManager;
import app.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AirportDataFacade {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired CsvManager csvManager;

    public AirportDataFacade() {}

    @PostConstruct
    public void Initialize() {
        if (this.hasData()){
            Logger.info("Database already loaded.");
            return;
        }

        List<Airport> airports = this.mapFromCsvToObject();
        this.enrichWithCountryName(airports);
        this.enrichWithLocation(airports);
        this.populateDatabase(airports);
    }

    private boolean hasData() {
        return this.airportRepository.findAll().size() > 0;
    }

    private void populateDatabase(List<Airport> airports) {
        try {
            Logger.info("Saving into database. Go grab a coffee...");
            Instant start = Instant.now();
            airports.forEach(airport -> airportRepository.save(airport));
            Instant finish = Instant.now();
            Logger.info("Successfully loaded " + airports.size() + " registers into database.");
            Logger.info("Database was updated in " + Logger.elapsedTime(start, finish));
        } catch (Exception ex) {
            Logger.error("Error loading database:" + ex.getMessage());
        }
    }

    private List<Airport> mapFromCsvToObject() {
        List<String[]> airports;
        List<Airport> mappedAirports = new LinkedList<Airport>();
        AtomicInteger index = new AtomicInteger(0);

        try {
            Logger.info("Started mapping enrichment...");
            airports = csvManager.readAll(Config.getAirportsCsv(), false);

            airports.forEach(line -> {
                String countryId = line[0];
                String iataCode = line[1];
                String name = line[2];
                String city = line[3];
                mappedAirports.add(new Airport(countryId, iataCode, name, city));
                index.getAndIncrement();
            });
            Logger.info("Finished mapping enrichment...");
        } catch (Exception ex) {
            Logger.error("Error mapping airport. Airport index: " + index.get());
        }

        return mappedAirports;
    }

    private List<String[]> mapObjectToCsv(List<Airport> airports) {
        List<String[]> airportsString = new ArrayList<String[]>();

        airports.forEach(airport -> {
                String[] line = new String[9];
                line[0] = airport.getIataCode();
                line[1] = airport.getCountry();
                line[2] = airport.getRegion();
                line[3] = airport.getCity();
                line[4] = airport.getIcaoCode();
                line[5] = airport.getLatitude().toString();
                line[6] = airport.getLongitude().toString();
                line[7] = airport.getUtcOffset();
                line[8] = airport.getTimezone();
                airportsString.add(line);
            }
        );
        return airportsString;
    }

    private List<Airport> enrichWithCountryName(List<Airport> airports) {
        List<String[]> countries;

        try {
            Logger.info("Started country enrichment...");
            countries = csvManager.readAll(Config.getCountriesCsv(), false);
            countries.stream()
                .filter( line -> line[0].equals("EN")) //remove hardcoded here and make a parameter (default can be EN).
                .forEach( line -> {
                String countryId = line[1];
                String countryName = line[2];

                airports.stream()
                    .filter(airport -> airport.getCountryId().equals(countryId))
                    .forEach(airport -> airport.setCountry(countryName));
            });
            Logger.info("Finished country enrichment...");
        } catch (Exception ex) {
            Logger.error("Error enriching airport with country: " + ex.getMessage());
        }

        return airports;
    }

    private List<Airport> enrichWithLocation(List<Airport> airports) {
        List<String[]> airportsWithLocationInfo;

        try {
            airportsWithLocationInfo = csvManager.readAll(Config.getOpenApiAirportsCsv(), false);
            Logger.info("Started location enrichment...");
            airportsWithLocationInfo.stream()
                .filter(line -> !line[4].equals("")) // line[5] is IATA Code.
                .filter(line -> !line[4].equals("N"))
                .forEach( line -> {
                String iataCode = line[4];
                double latitude = Double.parseDouble(line[6]);
                double longitude = Double.parseDouble(line[7]);
                String utcOffset = line[9];
                String timezone = line[11];

                airports.stream()
                    .filter(airport -> airport.getIataCode().equals(iataCode))
                    .forEach(airport -> {
                        airport.setLatitude(latitude);
                        airport.setLongitude(longitude);
                        airport.setUtcOffset(utcOffset);
                        airport.setTimezone(timezone);
                    });
            });
            Logger.info("Finished location enrichment...");
        } catch (Exception ex) {
            Logger.error("Error enriching airport with location information: " +  ex.getMessage());
        }

        return airports;
    }

    private void exportAllAirportsToCsv() {
        try {
            List<Airport> airports = airportRepository.findAll();
            List<String[]> airportsMappedToCsv = this.mapObjectToCsv(airports);
            csvManager.writeAll(Config.getFlightCompareAirportsCsv(), airportsMappedToCsv);
            Logger.info("Total of " + airportsMappedToCsv.size() + " airports have been exported.");
            Logger.info("Saved into file: " + Config.getAirportsCsv());
        }
        catch (Exception ex) {
            Logger.error("Error exporting csv: " + ex.getMessage());
        }
    }
}
