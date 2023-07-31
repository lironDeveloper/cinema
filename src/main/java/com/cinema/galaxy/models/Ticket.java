package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה משתמש נדרש.")
    @Size(min = 1, max = 255, message = "מזהה משתמש חייב להיות באורך של 1 עד 255 תווים.")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה הקרנה נדרש.")
    @Size(min = 1, max = 255, message = "מזהה הקרנה חייב להיות באורך של 1 עד 255 תווים.")
    private Showtime showtime;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank(message = "מזהה מושב נדרש.")
    @Size(min = 1, max = 255, message = "מזהה מושב חייב להיות באורך של 1 עד 255 תווים.")
    private Seat seat;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

//    public Ticket(User user, Showtime showtime, Seat seat) {
//        this.user = user;
//        this.showtime = showtime;
//        this.seat = seat;
//    }

}