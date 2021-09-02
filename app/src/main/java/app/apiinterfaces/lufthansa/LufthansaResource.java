package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaResource {
    @JsonProperty("AirportResource")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    LufthansaAirportResource airportResource;

    public LufthansaResource() {
    }

    public LufthansaAirportResource getAirportResource() {
        return airportResource;
    }

    public void setAirportResource(LufthansaAirportResource airportResource) {
        this.airportResource = airportResource;
    }
}
