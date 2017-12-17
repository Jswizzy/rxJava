package rxjava;

import io.reactivex.Observable;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TimePeriods {

    private static final LocalTime BUSINESS_START = LocalTime.of(9, 0);
    private static final LocalTime BUSINESS_END = LocalTime.of(17, 0);

    public static void main(String[] args) throws InterruptedException {


        Observable<Duration> insideBusinessHours = Observable
                .interval(1, SECONDS)
                .filter(x -> isBusinessHour())
                .map(x -> Duration.ofMillis(100));
        Observable<Duration> outsideBusinessHours = Observable
                .interval(5, SECONDS)
                .filter(x -> !isBusinessHour())
                .map(x -> Duration.ofMillis(200));

        Observable<Duration> openings = Observable.merge(
                insideBusinessHours, outsideBusinessHours);


        openings.subscribe(System.out::println);

        Thread.sleep(10000);

    }

    private static boolean isBusinessHour() {
        ZoneId zone = ZoneId.of("Europe/Warsaw");
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        LocalTime localTime = zdt.toLocalTime();
        return !localTime.isBefore(BUSINESS_START)
                && !localTime.isAfter(BUSINESS_END);
    }
}
