package com.svshayt.demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JavaTimePackageDemo {

    public static void main(String[] args) {
        durationDemo();
        instantDemo();
        localTimeDemo();
        localDateDemo();
        localDateTimeDemo();
        zonedDateTimeDemo();
        zoneOffsetDemo();
        zoneIdDemo();
        dateTimeFormatterDemo();
        periodDemo();
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


    public static void localTimeDemo() {
        LocalTime now = LocalTime.now(); // текущее время
        LocalTime specificTime = LocalTime.of(14, 30); // 14:30

        System.out.println(now);
        System.out.println(specificTime);
    }

    public static void localDateDemo() {
        LocalDate today = LocalDate.now(); // текущая дата
        LocalDate specificDate = LocalDate.of(2021, Month.JANUARY, 1); // 1 января 2021 года

        System.out.println(today);
        System.out.println(specificDate);

        //Сравнение дат
        LocalDate date1 = LocalDate.of(2021, Month.JANUARY, 1);
        LocalDate date2 = LocalDate.of(2022, Month.JANUARY, 1);

        boolean isBefore = date1.isBefore(date2); // true
        boolean isAfter = date1.isAfter(date2);   // false

        System.out.println(isBefore);
        System.out.println(isAfter);

        //Добавление и вычитание времени
        LocalDate today2 = LocalDate.now();
        LocalDate nextWeek = today2.plusWeeks(1); // добавляем неделю
        LocalDate lastMonth = today2.minusMonths(1); // вычитаем месяц

        System.out.println(today2);
        System.out.println(nextWeek);
        System.out.println(lastMonth);


        System.out.println("printDemoLocalDate_START");
        var now = LocalDate.now();
        System.out.println(now); // 2024-12-10
        System.out.println(now.atStartOfDay()); // 2024-12-10T00:00
        System.out.println("printDemoLocalDate_END");

        System.out.println("printMethodOfLocalDate_START");
        var now2 = LocalDate.now();
        System.out.println(now2); // 2024-12-10
        System.out.println(now2.atStartOfDay()); // 2024-12-10T00:00
        System.out.println("printMethodOfLocalDate_END");
    }

    public static void localDateTimeDemo() {
        LocalDateTime now = LocalDateTime.now(); // текущая дата и время
        LocalDateTime specificDateTime = LocalDateTime.of(2021, Month.JANUARY, 1, 14, 30); // 1 января 2021 года, 14:30

        System.out.println(now);
        System.out.println(specificDateTime);

        //Конвертация LocalDateTime в Instant
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        System.out.println(localDateTime);
        System.out.println(instant);
    }

    public static void zonedDateTimeDemo() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));

        System.out.println(zonedDateTime); // 2024-11-18T11:25:30.460883-05:00[America/New_York]

    }

    public static void zoneOffsetDemo() {
        ZoneOffset offset = ZoneOffset.ofHours(2);

        System.out.println(offset);
    }

    public static void zoneIdDemo() {
        ZoneId zoneId = ZoneId.systemDefault();

        System.out.println(zoneId); // Europe/Moscow
    }

    public static void dateTimeFormatterDemo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = LocalDateTime.now().format(formatter); // форматирование
        LocalDateTime parsedDate = LocalDateTime.parse("01-01-2021 14:30", formatter); // разбор строки

        System.out.println(formatter);
        System.out.println(formattedDate);
        System.out.println(parsedDate);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate2 = LocalDateTime.now().format(formatter2);
        LocalDateTime parsedDate2 = LocalDateTime.parse("2021-01-01 14:30:00", formatter2);

        System.out.println(formatter2);
        System.out.println(formattedDate2);
        System.out.println(parsedDate2);
    }

    public static void periodDemo() {
        Period period = Period.of(2, 3, 4); // 2 года, 3 месяца, 4 дня
        Period betweenDates = Period.between(LocalDate.of(2020, 1, 1), LocalDate.of(2022, 1, 1)); // разница между датами

        System.out.println(period); // P2Y3M4D
        System.out.println(betweenDates); //P2Y
    }
}
