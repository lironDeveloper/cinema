package com.cinema.galaxy.models;

import com.cinema.galaxy.models.Hall;
import com.cinema.galaxy.models.Movie;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Table
@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotBlank(message = "מזהה סרט נדרש.")
    @Size(min = 1, max = 255, message = "מזהה סרט חייב להיות באורך של 1 עד 255 תווים.")
    private Movie movie_id;
    @ManyToOne
    @NotBlank(message = "מזהה אולם נדרש.")
    @Size(min = 1, max = 255, message = "מזהה אולם חייב להיות באורך של 1 עד 255 תווים.")
    private Hall hall_id;
    @NotBlank(message = "תאריך תחילת ההקרנה נדרש.")
    @Future(message = "תאריך תחילת הקרנה חייב להיות בעתיד.")
    private Date start_time;
    @NotBlank(message = "תאריך סיום ההקרנה נדרש.")
    @Future(message = "תאריך סיום הקרנה חייב להיות בעתיד.")
    private Date end_time;

    public Showtime(Movie movie_id, Hall hall_id, Date start_time, Date end_time) {
        this.movie_id = movie_id;
        this.hall_id = hall_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Showtime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Movie movie_id) {
        this.movie_id = movie_id;
    }

    public Hall getHall_id() {
        return hall_id;
    }

    public void setHall_id(Hall hall_id) {
        this.hall_id = hall_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "id=" + id +
                ", movie_id=" + movie_id +
                ", hall_id=" + hall_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
