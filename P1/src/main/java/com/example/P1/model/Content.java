package com.example.P1.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

@Entity
public class Content {
    @Id
    private String id;
    private String title;
    private String link;

    private Time duration;
    private Date releaseDate;
    private float rating;

    public Content(String title, String link, Time duration, Date releaseDate, float rating) {
        rating = rating;
        title = title;
        link = link;
        releaseDate = releaseDate;
        rating = rating;
        duration = duration;

    }

    public Content() {

    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", name='" + title + '\'' +
                ", link='" + link + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
}
