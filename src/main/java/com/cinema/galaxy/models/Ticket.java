package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotBlank(message = "מזהה משתמש נדרש.")
    @Size(min = 1, max = 255, message = "מזהה משתמש חייב להיות באורך של 1 עד 255 תווים.")
    private User userId;
    @ManyToOne
    @NotBlank(message = "מזהה הקרנה נדרש.")
    @Size(min = 1, max = 255, message = "מזהה הקרנה חייב להיות באורך של 1 עד 255 תווים.")
    private Showtime showtimeId;
    @ManyToOne
    @NotBlank(message = "מזהה מושב נדרש.")
    @Size(min = 1, max = 255, message = "מזהה מושב חייב להיות באורך של 1 עד 255 תווים.")
    private Seat seatId;

    public Ticket() {
    }

    public Ticket(User userId, Showtime showtimeId, Seat seatId) {
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
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

    public Showtime getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Showtime showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Seat getSeatId() {
        return seatId;
    }

    public void setSeatId(Seat seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", showtimeId=" + showtimeId +
                ", seatId=" + seatId +
                '}';
    }
}
