package com.cinema.galaxy.DTOs.Branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class BranchDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String contactInfo;
    private Instant createdOn;
}
