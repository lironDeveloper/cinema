package com.cinema.galaxy.DTOs;

import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private Instant createdOn;
}
