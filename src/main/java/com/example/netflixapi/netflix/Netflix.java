package com.example.netflixapi.netflix;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Netflix {

    private static AtomicLong auto_id = new AtomicLong(0);
    private Long id;
    private String type;
    private String title;
    private String[] director;
    private String[] cast;
    private String[] country;
    private Long release_year;
    private String rating;
    private Integer duration;
    private String[] listed_in;

    public Netflix() {
    }

    public Netflix(Long id, String type, String title, String[] director, String[] cast, String[] country,
                   Long release_year, String rating, Integer duration, String[] listed_in) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
    }

    public Netflix(String type, String title, String[] director, String[] cast, String[] country,
                   Long release_year, String rating, Integer duration, String[] listed_in) {
        this.id = auto_id.incrementAndGet();
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public Long getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Long release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String[] getListed_in() {
        return listed_in;
    }

    public void setListed_in(String[] listed_in) {
        this.listed_in = listed_in;
    }

    @Override
    public String toString() {
        return "Netflix{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", director=" + Arrays.toString(director) +
                ", cast=" + Arrays.toString(cast) +
                ", country=" + Arrays.toString(country) +
                ", release_year='" + release_year + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", listed_in=" + Arrays.toString(listed_in) +
                '}';
    }
}
