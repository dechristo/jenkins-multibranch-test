package app;

public class Config {
    private static String lufthansaApiBaseUrl = "https://api.lufthansa.com/v1";
    private static String lufthansaClientId = "pdheqecm8h32aaydmhrhc7sf"; //API Key
    private static String lufthansaClientSecret = "9Gp257NY7C";
    private static String lufthansaGrantType = "client_credentials";
    private static String airportsCsv = "airports.csv";
    private static String countriesCsv = "countries.csv";
    private static String openApiAirportsCsv = "openapi-airports.csv";
    private static String flightCompareAirportsCsv = "flightcompare-airports.csv";
    private static String authApiToken = "LqrXNHCEw0";

    public static String getLufthansaApiBaseUrl() {
        return lufthansaApiBaseUrl;
    }

    public static String getLufthansaClientId() {
        return lufthansaClientId;
    }

    public static String getLufthansaClientSecret() {
        return lufthansaClientSecret;
    }

    public static String getLufthansaGrantType() {
        return lufthansaGrantType;
    }

    public static String getAirportsCsv() { return airportsCsv; }

    public static String getCountriesCsv() { return countriesCsv; }

    public static String getOpenApiAirportsCsv() { return openApiAirportsCsv; }

    public static String getFlightCompareAirportsCsv() { return flightCompareAirportsCsv; }

    public static String getAuthApiToken() { return authApiToken; }
}
