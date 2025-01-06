package com.svshayt.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaNioPackageDemo {

    public static void main(String[] args) {
        standardCharsetsDemo();
        bufferedReaderDemo();
        bufferedReader2Demo();
    }

    public static void standardCharsetsDemo() {
        var charset = StandardCharsets.UTF_8;
        System.out.println(charset);
    }

    public static void bufferedReaderDemo() {
        try {
            String path = "demo/large_text.txt";
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)
            );
            reader.lines().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void bufferedReader2Demo() {
        try {
            String path = "demo/small_text.txt";
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)
            );
            var content = reader.lines().collect(Collectors.joining("\n"));
            System.out.println(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
