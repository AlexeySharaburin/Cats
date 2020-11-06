package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private final String first;
    private final String last;

    public Person(
            @JsonProperty("first") String first,
            @JsonProperty("last") String last
    ) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return  " {" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
