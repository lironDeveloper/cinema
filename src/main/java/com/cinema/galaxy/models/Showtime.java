package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Table
@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotBlank(message = "מזהה סרט נדרש.")
    @Size(min = 1, max = 255, message = "מזהה סרט חייב להיות באורך של 1 עד 255 תווים.")
    private Movie movieId;
    @ManyToOne
    @NotBlank(message = "מזהה אולם נדרש.")
    @Size(min = 1, max = 255, message = "מזהה אולם חייב להיות באורך של 1 עד 255 תווים.")
    private Hall hallId;
    @NotNull
    @Future(message = "תאריך תחילת הקרנה חייב להיות בעתיד.")
    private LocalDateTime startTime;
    @NotNull
    @Future(message = "תאריך סיום הקרנה חייב להיות בעתיד.")
    private LocalDateTime endTime;

    public Showtime(Movie movieId, Hall hallId, LocalDateTime startTime, LocalDateTime endTime) {
        this.movieId = movieId;
        this.hallId = hallId;
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
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
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

    @Override
    public String toString() {
        return "Showtime{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", hallId=" + hallId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
