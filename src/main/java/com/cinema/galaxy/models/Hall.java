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

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "מזהה סניף נדרש.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
    @NotBlank(message = "שם האולם נדרש.")
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;
    @NotNull
    @Min(value = 1, message = "נדרש לפחות מושב אחד באולם.")
    private Integer capacity;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

//    public Hall(Branch branch, String name, int capacity) {
//        this.branch = branch;
//        this.name = name;
//        this.capacity = capacity;
//    }
}
