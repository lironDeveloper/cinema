package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Table
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movieId;
    @NotNull
    @Range(min = 1, max = 5, message = "דירוג הביקורת הוא מספר בין 1 ל5.")
    private Integer rating;
    @NotBlank(message = "תגובה לביקורת נדרשת.")
    @Size(min = 5, max = 255, message = "תגובה לביקורת חייבת להיות באורך של 5 עד 255 תווים.")
    private String comment;

    public Review() {
    }

    public Review(User userId, Movie movieId, int rating, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
