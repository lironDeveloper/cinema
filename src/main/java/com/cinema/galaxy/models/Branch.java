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
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 50, message = "שם סניף חייב להיות באורך של 2-50 תווים.")
    @Column(unique = true, nullable = false)
    private String name;
    @Size(min = 2, max = 50, message = "שם עיר חייב להיות באורך של 2-50 תווים.")
    @Column(nullable = false)
    private String city;
    @Size(min = 2, max = 150, message = "כתובת חייבת להיות באורך של 2-150 תווים.")
    @Column(nullable = false)
    private String address;
    @Size(min = 2, max = 50, message = "שם איש קשר חייב להיות באורך של 2-50 תווים.")
    @Column(name = "contact_info", nullable = false)
    private String contactInfo;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hall> halls = new ArrayList<>();
}
