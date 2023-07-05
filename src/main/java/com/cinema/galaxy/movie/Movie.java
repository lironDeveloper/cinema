package com.cinema.galaxy.movie;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import jakarta.persistence.*;

import java.util.Date;

@Table
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int duration; // In minutes
    private Date release_date;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String director;
    @Enumerated(EnumType.STRING)
    private Language language;
    private int min_age;

    public Movie() {
    }

    public Movie(String title, String description, int duration, Date release_date, Genre genre, String director, Language language, int min_age) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.release_date = release_date;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.min_age = min_age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", release_date=" + release_date +
                ", genre=" + genre +
                ", director='" + director + '\'' +
                ", language=" + language +
                ", min_age=" + min_age +
                '}';
    }
}
