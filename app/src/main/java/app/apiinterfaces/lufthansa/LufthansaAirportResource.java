package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaAirportResource {
    @JsonProperty("Airports")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private LufthansaAirports airports;

    public LufthansaAirportResource() {
    }

    public LufthansaAirports getAirports() {
        return airports;
    }

}
