package com.cinema.galaxy.review;

import com.cinema.galaxy.enums.MovieRating;
import com.cinema.galaxy.movie.Movie;
import com.cinema.galaxy.user.User;
import jakarta.persistence.*;

@Table
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user_id;
    @ManyToOne
    private Movie movie_id;
    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    private String comment;

    public Review() {
    }

    public Review(User user_id, Movie movie_id, MovieRating rating, String comment) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Movie getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Movie movie_id) {
        this.movie_id = movie_id;
    }

    public MovieRating getRating() {
        return rating;
    }

    public void setRating(MovieRating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", movie_id=" + movie_id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
