package com.cinema.galaxy.DTOs;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationDTO {
    @NotBlank(message = "אימייל נדרש.")
    @Email(message = "פורמט לא תקין של אימייל.")
    private String email;
    @NotBlank(message = "שם פרטי נדרש.")
    @Size(min = 5, max = 50, message = "שם פרטי חייב להיות באורך של 5 עד 50 תווים.")
    private String firstName;
    @NotBlank(message = "שם משפחה נדרש.")
    @Size(min = 5, max = 50, message = "שם משפחה חייב להיות באורך של 5 עד 50 תווים.")
    private String lastName;
    @Size(min = 8, max = 16, message = "סיסמא חייבת להיות באורך של 8 עד 16 תווים.")
    @NotBlank(message = "סיסמא נדרשת.")
    private String password;
    @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.")
    private String role;
}
