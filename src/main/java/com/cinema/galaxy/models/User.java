package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "אימייל נדרש.")
    @Email(message = "פורמט לא תקין של אימייל.")
    private String email;
    @NotBlank(message = "שם פרטי נדרש.")
    @Size(min = 5, max = 50, message = "שם פרטי חייב להיות באורך של 5 עד 50 תווים.")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "שם משפחה נדרש.")
    @Size(min = 5, max = 50, message = "שם משפחה חייב להיות באורך של 5 עד 50 תווים.")
    @Column(name = "last_name")
    private String lastName;
    private String password;
    @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.")
    private String role;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }
}
