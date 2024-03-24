package com.example.P1.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

/**
 * entity class for content containing:
 * id, title, link, duration, release date and rating
 * entity that stores all the information relevant to be
 * displayed about the content
 */
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
