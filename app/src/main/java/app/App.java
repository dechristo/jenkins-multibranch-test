package app;

import app.facade.AirportDataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class App {

    @Autowired
    AirportDataFacade airportDataFacade;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
