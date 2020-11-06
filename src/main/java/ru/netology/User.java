package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private final String _id;
    private final Person person;

    public User(
            @JsonProperty("_id") String _id,
            @JsonProperty("name") Person person
    ) {
        this._id = _id;
        this.person = person;
    }

    public String get_id() {
        return _id;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return  "User {" +
                "_id='" + _id + '\'' +
                ", person=" + person +
                '}';
    }
}
