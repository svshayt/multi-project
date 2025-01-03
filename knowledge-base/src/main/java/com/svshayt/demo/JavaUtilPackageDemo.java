package com.svshayt.demo;

import java.util.ArrayList;

public class JavaUtilPackageDemo {

    public static void main(String[] args) {
        listDemo();
    }

    public static void listDemo() {
        var list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);
        System.out.println(list.getFirst());
    }
}
