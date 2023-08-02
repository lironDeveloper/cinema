package com.cinema.galaxy.DTOs.Hall;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HallCreationDTO {
    @NotNull(message = "יש לציין מזהה סניף.")
    private Long branchId;
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;
    @Min(value = 1, message = "נדרש לפחות שורה אחת באולם.")
    private Integer numOfRows;
    @Min(value = 1, message = "נדרש לפחות עמודה אחת באולם.")
    private Integer numOfColumns;
}
