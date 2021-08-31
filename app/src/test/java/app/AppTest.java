package app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = {RestTemplate.class})
public class AppTest {

    @Test
    public void contextLoads() {
    }
}
