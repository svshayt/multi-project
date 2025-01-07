package com.svshayt.demo;

import com.svshayt.model.Person;

import java.util.ArrayList;

public class JavaUtilPackageDemo {

    public static void main(String[] args) {
        listDemo();
        objectDemo();
    }

    public static void listDemo() {
        var list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);
        System.out.println(list.getFirst());
    }

    public static void objectDemo() {
        var person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        var personTheSame = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        System.out.println(person.toString()); // Person(firstName=John, lastName=Doe, age=null, email=null, phone=null, address=null, city=null, country=null, birthDate=null, birthPlace=null)
        System.out.println(person.equals(personTheSame)); // false
        System.out.println(person.hashCode()); // 1599771323
        System.out.println(person.getClass()); // class com.svshayt.model.Person

    }
}
