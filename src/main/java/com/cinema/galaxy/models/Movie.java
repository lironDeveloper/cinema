package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
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
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100, message = "שם סרט חייב להיות באורך של 2-100 תווים.")
    @Column(unique = true, nullable = false)
    private String title;
    @Size(min = 2, max = 254, message = "תיאור סרט חייב להיות באורך של 2-254 תווים.")
    @Column(nullable = false)
    private String description;
    @Range(min = 1, max=300, message = "אורך סרט חייב להיות בין דקה ל5 שעות.")
    @Column(nullable = false)
    private Integer duration; // In minutes
    @PastOrPresent(message = "תאריך הוצאת סרט חייב להיות בעבר.")
    @Column(name = "release_date", nullable = false)
    private LocalDateTime releaseDate;
    @ValidEnumValue(enumClass = Genre.class, message = "יש לבחור זאנ'ר חוקי.")
    @Column(nullable = false)
    private String genre;
    @Size(min = 2, max = 100, message = "שם במאי של הסרט חייב להיות באורך של 2-100 תווים.")
    @Column(nullable = false)
    private String director;
    @ValidEnumValue(enumClass = Language.class, message = "יש לבחור שפה חוקית.")
    @Column(nullable = false)
    private String language;
    @Range(min = 0, max=18, message = "גיל מינימאלי חייב להיות בין 0 ל18.")
    @Column(name = "min_age", nullable = false)
    private Integer minAge;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
    private MovieThumbnail movieThumbnail;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showtime> showtimeList = new ArrayList<>();
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
