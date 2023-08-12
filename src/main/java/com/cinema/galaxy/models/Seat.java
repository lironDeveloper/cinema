package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "hall_id", "row_num", "col_num" }) })
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;
    @Min(value = 1, message = "מספר שורה של מושב חייב להיות גדול מ0.")
    @Column(name = "row_num",nullable = false)
    private Integer rowNum;
    @Min(value = 1, message = "מספר עמודה של מושב חייב להיות גדול מ0.")
    @Column(name = "col_num", nullable = false)
    private Integer colNum;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

}
