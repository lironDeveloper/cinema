package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "פורמט לא תקין של אימייל.")
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 2, max = 50, message = "שם פרטי חייב להיות באורך של 2 עד 50 תווים.")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Size(min = 2, max = 50, message = "שם משפחה חייב להיות באורך של 2 עד 50 תווים.")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Size(min = 8, message = "סיסמא חייבת להיות באורך של מעל 8 תווים.")
    @Column(nullable = false)
    private String password;
    @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.")
    @Column(nullable = false)
    private String role;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}