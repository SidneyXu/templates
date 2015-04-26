package com.example.java.lib;

/**
 * Created by mrseasons on 2015/4/25.
 */
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String hello() {
        return "Hello, I'm " + name;
    }

    @Override
    public String toString() {
        return name;
    }
}
