package com.svshayt.knowledgebase.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
public class Person {
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String email;
    private final String phone;
    private final String address;
    private final String city;
    private final String country;
    private final LocalDate birthDate;
    private final String birthPlace;
}

