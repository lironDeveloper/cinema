package com.cinema.galaxy.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthRequestDTO {
    @NotBlank(message = "אימייל נדרש.")
    @Email(message = "פורמט לא תקין של אימייל.")
    private String email;
    @NotBlank(message = "סיסמא נדרשת.")
    private String password;
}
