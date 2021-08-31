package app.apiinterfaces.lufthansa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LufthansaAirport {
    @JsonProperty("AirportCode")
    private String code;

    @JsonProperty("Position")
    private LufthansaAirportPosition position;

    @JsonProperty("CityCode")
    private String cityCode;

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("LocationType")
    private String locationType;

    @JsonProperty("Names")
    private LufthansaAirportNames names;

    @JsonProperty("UtcOffset")
    private String utcOffset;

    @JsonProperty("TimeZoneId")
    private String timeZoneId;

    public LufthansaAirport() {}

    public String getCode() {
        return code;
    }

    public LufthansaAirportNames getNames() {
        return names;
    }

    public String getLocationType() { return locationType; }

    public LufthansaAirportPosition getPosition() {
        return position;
    }

    public void setPosition(LufthansaAirportPosition position) {
        this.position = position;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
}
