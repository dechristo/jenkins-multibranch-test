package app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Airport implements Serializable {

    @Id
    private String iataCode;
    private String icaoCode;
    private String name;
    @JsonIgnore
    private String countryId;
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;
    private String utcOffset;
    private String timezone;

    public Airport() {

    }

    public Airport(String countryId, String iataCode, String name, String city) {
        this.countryId = countryId;
        this.iataCode = iataCode;
        this.name = name;
        this.city = city;
    }

    public Airport(String iataCode, String icaoCode, String country,
        String region, String city, Double latitude, Double longitude,
        String utcOffset, String timezone) {

        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utcOffset = utcOffset;
        this.timezone = timezone;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCountryId() { return countryId; }

    public void setCountryId(String countryId) { this.countryId = countryId; }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
