package app.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AirportTest {

    @Test
    public void defaultConstructorReturnsObjectWithAttributeValuesSetToNull() {
        Airport airport = new Airport();

        assertNull(airport.getIataCode());
        assertNull(airport.getCountry());
        assertNull(airport.getRegion());
        assertNull(airport.getCity());
        assertNull(airport.getLatitude());
        assertNull(airport.getLongitude());
        assertNull(airport.getUtcOffset());
        assertNull(airport.getTimezone());
    }

    @Test
    public void constructorReturnsObjectWithAttributeValuesSetCorrectly() {
        Airport airport = new Airport("CAN", "MMUN", "MX", "REG", "Cancun",
            513213.53878855, 2326063.3312096, "-05:00", "America/Cancun");

        assertEquals("CAN", airport.getIataCode());
        assertEquals("MMUN", airport.getIcaoCode());
        assertEquals("MX", airport.getCountry());
        assertEquals("REG", airport.getRegion());
        assertEquals("Cancun", airport.getCity());
        assertEquals(513213.53878855, airport.getLatitude());
        assertEquals(2326063.3312096, airport.getLongitude());
        assertEquals("-05:00",airport.getUtcOffset());
        assertEquals("America/Cancun", airport.getTimezone());
    }

    @Test
    public void setAndGetForIataCodeCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setIataCode("CAN");
        assertEquals("CAN", airport.getIataCode());
    }

    @Test
    public void setAndGetForIcaoCodeCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setIcaoCode("MMUN");
        assertEquals("MMUN", airport.getIcaoCode());
    }

    @Test
    public void setAndGetForCountryIdCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setCountry("MX");
        assertEquals("MX", airport.getCountry());
    }

    @Test
    public void setAndGetForRegionCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setRegion("REG");
        assertEquals("REG", airport.getRegion());
    }

    @Test
    public void setAndGetForCityCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setCity("Cancun");
        assertEquals("Cancun", airport.getCity());
    }

    @Test
    public void setAndGetForLatitudeCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setLatitude(12345.365478);
        assertEquals(12345.365478, airport.getLatitude());
    }

    @Test
    public void setAndGetForLongitudeCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setLongitude(987.999999);
        assertEquals(987.999999, airport.getLongitude());
    }

    @Test
    public void setAndGetForUtcOffsetCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setUtcOffset("-05:00");
        assertEquals("-05:00", airport.getUtcOffset());
    }

    @Test
    public void setAndGetForTimezoneCorrectlySetsAndGetsValue() {
        Airport airport = new Airport();

        airport.setTimezone("America/Cancun");
        assertEquals("America/Cancun", airport.getTimezone());
    }
}
