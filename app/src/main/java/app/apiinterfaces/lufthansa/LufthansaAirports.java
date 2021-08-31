package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LufthansaAirports {

    @JsonProperty("Airport")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<LufthansaAirport> airport;

    public LufthansaAirports() {}

    public List<LufthansaAirport> getAirport() {
        return airport;
    }
}
