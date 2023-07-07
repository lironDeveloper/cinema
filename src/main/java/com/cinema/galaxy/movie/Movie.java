package com.cinema.galaxy.movie;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Table
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "שם סרט נדרש.")
    @Size(min = 2, max = 100, message = "שם סרט חייב להיות באורך של 2-100 תווים.")
    private String title;
    @NotBlank(message = "תיאור של סרט נדרש.")
    @Size(min = 2, max = 254, message = "תיאור סרט חייב להיות באורך של 2-254 תווים.")
    private String description;

    @NotBlank(message = "אורך סרט נדרש.")
    @Range(min = 1, max=300, message = "אורך סרט חייב להיות בין דקה ל5 שעות.")
    private int duration; // In minutes

    @NotBlank(message = "תאריך הוצאה של סרט נדרש.")
    @PastOrPresent(message = "תאריך הוצאת סרט חייב להיות בעבר.")
    private Date release_date;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "ז'אנר של סרט נדרש.")
    // TODO: Validation for enum
    private Genre genre;

    @NotBlank(message = "שם במאי של הסרט נדרש.")
    @Size(min = 2, max = 100, message = "שם במאי של הסרט חייב להיות באורך של 2-100 תווים.")
    private String director;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "שפת הסרט נדרשת.")
    // TODO: Validation for enum
    private Language language;

    @NotBlank(message = "גיל מינימאלי נדרש.")
    @Range(min = 0, max=18, message = "גיל מינימאלי חייב להיות בין 0 ל18.")
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
