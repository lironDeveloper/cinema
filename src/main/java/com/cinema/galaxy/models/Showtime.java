package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "movie", "hall", "start_time", "end_time" }) })
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה סרט נדרש.")
    @Size(min = 1, max = 255, message = "מזהה סרט חייב להיות באורך של 1 עד 255 תווים.")
    @JoinColumn(name = "movie")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה אולם נדרש.")
    @Size(min = 1, max = 255, message = "מזהה אולם חייב להיות באורך של 1 עד 255 תווים.")
    @JoinColumn(name = "hall")
    private Hall hall;
    @NotNull
    @Future(message = "תאריך תחילת הקרנה חייב להיות בעתיד.")
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @NotNull
    @Future(message = "תאריך סיום הקרנה חייב להיות בעתיד.")
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

//    public Showtime(Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
//        this.movie = movie;
//        this.hall = hall;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }
}
