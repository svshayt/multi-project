package com.svshayt.demo;

import java.time.Duration;
import java.time.Instant;


public class JavaTimePackageDemo {

    public static void main(String[] args) {
        durationDemo();
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
}
