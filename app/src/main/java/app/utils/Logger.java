package app.utils;

import app.enums.Colors;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Logger {

    public static void info(String msg) {
        System.out.print(Colors.GREEN);
        System.out.println("["  + now() + "]: INFO --> "+ msg);
        System.out.print(Colors.RESET);
    }

    public static void error(String error) {
        System.out.print(Colors.RED);
        System.out.println("["  + now() + "]: ERROR --> "+ error);
        System.out.print(Colors.RESET);
    }

    private static String now() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static String elapsedTime(Instant start, Instant finish) {
        String result = "";

        long elapsedTime = Duration.between(start, finish).toMillis();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = elapsedTime / daysInMilli;
        elapsedTime = elapsedTime % daysInMilli;

        long elapsedHours = elapsedTime / hoursInMilli;
        elapsedTime = elapsedTime % hoursInMilli;

        long elapsedMinutes = elapsedTime / minutesInMilli;
        elapsedTime = elapsedTime % minutesInMilli;

        long elapsedSeconds = elapsedTime / secondsInMilli;

        if (elapsedDays > 0) result += elapsedDays + (elapsedDays > 1 ? " days " : " day ");
        if (elapsedHours > 0) result += elapsedHours + (elapsedHours > 1 ? " hours " : " hour ");
        if (elapsedMinutes > 0) result += elapsedMinutes + (elapsedMinutes > 1 ? " minutes " : " minute ");
        if (elapsedSeconds > 0) result += elapsedSeconds + (elapsedSeconds > 1 ? " seconds" : " second");

        return(result);
    }
}
