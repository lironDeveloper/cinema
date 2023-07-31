package com.cinema.galaxy.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationDTO {
    private String firstName;
    private String lastName;
    private String email;
    @Size(min = 8, max = 16, message = "סיסמא חייבת להיות באורך של 8 עד 16 תווים.")
    @NotBlank(message = "סיסמא נדרשת.")
    private String password;
    private String role;
}
