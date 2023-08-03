package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

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
    @NotNull
    @Min(value = 1, message = "מספר שורה של מושב חייב להיות גדול מ0.")
    @Column(name = "row_num")
    private Integer rowNum;
    @NotNull
    @Min(value = 1, message = "מספר עמודה של מושב חייב להיות גדול מ0.")
    @Column(name = "col_num")
    private Integer colNum;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

}
