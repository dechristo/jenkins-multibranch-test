package app.repositories;

import app.models.Airport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Integer> {
    List<Airport> findAll();
    List<Airport> findByNameIgnoreCase(String airportName);
    List<Airport> findByCountryIgnoreCase(String countryName);
    List<Airport> findByCityIgnoreCase(String cityName);

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE airport a SET" +
            " a.latitude = :latitude, a.longitude = :longitude, a.utc_offset = :utcOffset, a.timezone = :timezone" +
            " WHERE a.iata_code = :iataCode",
        nativeQuery = true)
    int updateAirport(
        @Param("iataCode") String iataCode,
        @Param("latitude") double latitude,
        @Param("longitude") double longitude,
        @Param("utcOffset") String utcOffset,
        @Param("timezone") String timezone
    );
}
