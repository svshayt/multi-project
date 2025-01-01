package com.svshayt.demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JavaTimePackageDemo {

    public static void main(String[] args) {
        durationDemo();
        instantDemo();
    }

    public static void durationDemo() {
        Duration duration = Duration.ofMinutes(5); // 5 минут
        Duration betweenInstants = Duration.between(
                Instant.now(),
                Instant.now().plusSeconds(10)
        ); // разница между моментами времени

        System.out.println(duration); // PT5M
        System.out.println(betweenInstants); // PT10.000001S
    }

    public static void instantDemo() {
        Instant now = Instant.now();
        Instant start = Instant.now();
        System.out.println("Текущее время: " + now);

        Instant fromEpochSecond = Instant.ofEpochSecond(1609459200); // 1 января 2021 года, 00:00:00 UTC
        Instant fromEpochMilli = Instant.ofEpochMilli(1609459200000L); // 1 января 2021 года, 00:00:00 UTC
        System.out.println("Из epoch seconds: " + fromEpochSecond);
        System.out.println("Из epoch milliseconds: " + fromEpochMilli);

        Instant later = now.plusSeconds(3600); // добавить 1 час (3600 секунд)
        Instant earlier = now.minusMillis(5000); // вычесть 5000 миллисекунд (5 секунд)
        System.out.println("Через час: " + later);
        System.out.println("5 секунд назад: " + earlier);

        Instant instant1 = Instant.now();
        Instant instant2 = instant1.plusSeconds(10);

        if (instant1.isBefore(instant2)) {
            System.out.println("instant1 произошел раньше, чем instant2");
        } else if (instant1.isAfter(instant2)) {
            System.out.println("instant1 произошел позже, чем instant2");
        }

        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

        System.out.println("Временная зона: " + zonedDateTime);
        System.out.println("Локальное время: " + localDateTime);

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Время выполнения: " + duration.toMillis() + " миллисекунд");
    }


}
