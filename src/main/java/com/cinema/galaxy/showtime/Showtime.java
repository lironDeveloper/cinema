package com.cinema.galaxy.showtime;

import com.cinema.galaxy.hall.Hall;
import com.cinema.galaxy.movie.Movie;
import jakarta.persistence.*;

import java.util.Date;

@Table
@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Movie movie_id;
    @ManyToOne
    private Hall hall_id;
    private Date start_time;
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
