package com.cinema.galaxy.DTOs.Hall;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "שם אולם נדרש.")
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;
    @NotNull(message = "יש לציין מספר שורות באולם.")
    @Min(value = 1, message = "נדרש לפחות שורה אחת באולם.")
    private Integer numOfRows;
    @NotNull(message = "יש לציין מספר עמודות באולם.")
    @Min(value = 1, message = "נדרש לפחות עמודה אחת באולם.")
    private Integer numOfColumns;
}
