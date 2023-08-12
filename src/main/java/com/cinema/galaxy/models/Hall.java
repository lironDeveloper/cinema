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
import java.util.ArrayList;
import java.util.List;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "branch_id", "name" }) })
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    @Column(nullable = false)
    private String name;
    @Min(value = 1, message = "נדרש לפחות שורה אחת באולם.")
    @Column(name = "rows_num", nullable = false)
    private Integer numOfRows;
    @Min(value = 1, message = "נדרש לפחות עמודה אחת באולם.")
    @Column(name = "cols_num", nullable = false)
    private Integer numOfColumns;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showtime> showtimeList = new ArrayList<>();
}
