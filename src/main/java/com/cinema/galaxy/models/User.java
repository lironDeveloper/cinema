package com.cinema.galaxy.models;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "אימייל נדרש.")
    @Email(message = "פורמט לא תקין של אימייל.")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "שם פרטי נדרש.")
    @Size(min = 5, max = 50, message = "שם פרטי חייב להיות באורך של 5 עד 50 תווים.")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "שם משפחה נדרש.")
    @Size(min = 5, max = 50, message = "שם משפחה חייב להיות באורך של 5 עד 50 תווים.")
    @Column(name = "last_name")
    private String lastName;
    @Size(min = 8, max = 16, message = "סיסמא חייבת להיות באורך של 8 עד 16 תווים.")
    @NotBlank(message = "סיסמא נדרשת.")
    private String password;
    @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.")
    private String role;
    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private Instant lastUpdatedOn;
}