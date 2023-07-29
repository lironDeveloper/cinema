package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Table
@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה סרט נדרש.")
    @Size(min = 1, max = 255, message = "מזהה סרט חייב להיות באורך של 1 עד 255 תווים.")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה אולם נדרש.")
    @Size(min = 1, max = 255, message = "מזהה אולם חייב להיות באורך של 1 עד 255 תווים.")
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

    public Showtime(Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Showtime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovieId() {
        return movie;
    }

    public void setMovieId(Movie movie) {
        this.movie = movie;
    }

    public Hall getHallId() {
        return hall;
    }

    public void setHallId(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }
}
