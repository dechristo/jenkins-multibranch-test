package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaAirportCoordinate {
    @JsonProperty("Latitude")
    private double latitude;

    @JsonProperty("Longitude")
    private double longitude;

    public LufthansaAirportCoordinate() {}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
