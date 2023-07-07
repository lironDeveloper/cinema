package com.cinema.galaxy.ticket;

import com.cinema.galaxy.seat.Seat;
import com.cinema.galaxy.showtime.Showtime;
import com.cinema.galaxy.user.User;
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
    private User user_id;
    @ManyToOne
    @NotBlank(message = "מזהה הקרנה נדרש.")
    @Size(min = 1, max = 255, message = "מזהה הקרנה חייב להיות באורך של 1 עד 255 תווים.")
    private Showtime showtime_id;
    @ManyToOne
    @NotBlank(message = "מזהה מושב נדרש.")
    @Size(min = 1, max = 255, message = "מזהה מושב חייב להיות באורך של 1 עד 255 תווים.")
    private Seat seat_id;

    public Ticket() {
    }

    public Ticket(User user_id, Showtime showtime_id, Seat seat_id) {
        this.user_id = user_id;
        this.showtime_id = showtime_id;
        this.seat_id = seat_id;
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

    public Showtime getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(Showtime showtime_id) {
        this.showtime_id = showtime_id;
    }

    public Seat getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Seat seat_id) {
        this.seat_id = seat_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", showtime_id=" + showtime_id +
                ", seat_id=" + seat_id +
                '}';
    }
}
