package com.cinema.galaxy.DTOs.User;

import com.cinema.galaxy.enums.Role;
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
    private Role role;
    private Instant createdOn;
}
