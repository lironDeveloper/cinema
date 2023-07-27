package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.persistence.*;
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
    @NotBlank(message = "שם משתמש נדרש.")
    @Size(min = 5, max = 50, message = "שם משתמש חייב להיות באורך של 5 עד 50 תווים.")
    private String username;
    @NotBlank(message = "סיסמא נדרשת.") // TODO: probably should be only in UserCreationDTO
    @Size(min = 8, max = 16, message = "סיסמא חייבת להיות באורך של 8 עד 16 תווים.")
    private String password;
    @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.")
    private String role;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }


}
