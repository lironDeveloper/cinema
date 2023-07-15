package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

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
    @Range(min = 1, max=300, message = "אורך סרט חייב להיות בין דקה ל5 שעות.")
    @NotNull
    private Integer duration; // In minutes
    @NotNull
    @PastOrPresent(message = "תאריך הוצאת סרט חייב להיות בעבר.")
    private LocalDateTime releaseDate;
    @ValidEnumValue(enumClass = Genre.class, message = "יש לבחור זאנ'ר חוקי.")
    private String genre;
    @NotBlank(message = "שם במאי של הסרט נדרש.")
    @Size(min = 2, max = 100, message = "שם במאי של הסרט חייב להיות באורך של 2-100 תווים.")
    private String director;
    @ValidEnumValue(enumClass = Language.class, message = "יש לבחור שפה חוקית.")
    private String language;
    @Range(min = 0, max=18, message = "גיל מינימאלי חייב להיות בין 0 ל18.")
    @NotNull
    private Integer minAge;

    public Movie() {
    }

    public Movie(String title, String description, int duration, LocalDateTime releaseDate, String genre, String director, String language, int minAge) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.minAge = minAge;
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

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", director='" + director + '\'' +
                ", language=" + language +
                ", minAge=" + minAge +
                '}';
    }
}
