package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaAirportName {
    @JsonProperty("@LanguageCode")
    private String languageCode;

    @JsonProperty("$")
    private String airportName;

    public LufthansaAirportName() {}

    public String getLanguageCode() {
        return languageCode;
    }

    public String getAirportName() {
        return airportName;
    }

}
