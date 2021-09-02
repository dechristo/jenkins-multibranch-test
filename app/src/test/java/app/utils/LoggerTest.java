package app.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class LoggerTest {
    @Test
    public void shouldReturnElapsedTimeInFull() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
        Instant start = Instant.ofEpochMilli(df.parse("May 04 2020 06:24:37").getTime());
        Instant finish = Instant.ofEpochMilli(df.parse("May 07 2020 19:07:02").getTime());
        assertEquals("3 days 12 hours 42 minutes 25 seconds",Logger.elapsedTime(start,finish));
    }
}
