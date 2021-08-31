package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LufthansaAirportNames {
    @JsonProperty("Name")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)

    private List<LufthansaAirportName> name;

    public LufthansaAirportNames() {}

    public List<LufthansaAirportName> getName() {
        return name;
    }
}
