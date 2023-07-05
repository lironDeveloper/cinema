package com.cinema.galaxy.ticket;

import com.cinema.galaxy.seat.Seat;
import com.cinema.galaxy.showtime.Showtime;
import com.cinema.galaxy.user.User;
import jakarta.persistence.*;

@Table
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user_id;
    @ManyToOne
    private Showtime showtime_id;
    @ManyToOne
    private Seat seat_id;
}
