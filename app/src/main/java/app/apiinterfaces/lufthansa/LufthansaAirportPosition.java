package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaAirportPosition {

    @JsonProperty("Coordinate")
    private LufthansaAirportCoordinate coordinate;

    public LufthansaAirportPosition(){}

    public LufthansaAirportCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LufthansaAirportCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
