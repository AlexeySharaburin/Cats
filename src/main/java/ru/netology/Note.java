package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {
    private final String _id;
    private final String text;
    private final String type;
    private final User user;
    private final int upvotes;
    private final String userUpvoted;

    public Note(
            @JsonProperty("_id") String _id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") User user,
            @JsonProperty("upvotes") int upvotes,
            @JsonProperty("userUpvoted") String userUpvoted
    ) {
        this._id = _id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
        this.userUpvoted = userUpvoted;
    }


    public String get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getUserUpvoted() {
        return userUpvoted;
    }

    @Override
    public String toString() {
        return  "\nЗапись {" +
                "_id='" + _id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user=" + user +
                ", upvotes='" + upvotes + '\'' +
                ", userUpvoted='" + userUpvoted + '\'' +
                '}';
    }
}
