package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.Instant;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "שם סרט נדרש.")
    @Size(min = 2, max = 100, message = "שם סרט חייב להיות באורך של 2-100 תווים.")
    @Column(unique = true)
    private String title;
    @NotBlank(message = "תיאור של סרט נדרש.")
    @Size(min = 2, max = 254, message = "תיאור סרט חייב להיות באורך של 2-254 תווים.")
    private String description;
    @Range(min = 1, max=300, message = "אורך סרט חייב להיות בין דקה ל5 שעות.")
    @NotNull
    private Integer duration; // In minutes
    @NotNull
    @PastOrPresent(message = "תאריך הוצאת סרט חייב להיות בעבר.")
    @Column(name = "release_date")
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
    @Column(name = "min_age")
    private Integer minAge;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

//    public Movie(String title, String description, int duration, LocalDateTime releaseDate, String genre, String director, String language, int minAge) {
//        this.title = title;
//        this.description = description;
//        this.duration = duration;
//        this.releaseDate = releaseDate;
//        this.genre = genre;
//        this.director = director;
//        this.language = language;
//        this.minAge = minAge;
//    }
}
