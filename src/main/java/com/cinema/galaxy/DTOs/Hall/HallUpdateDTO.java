package com.cinema.galaxy.DTOs.Hall;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HallUpdateDTO {
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;
    @Min(value = 1, message = "נדרש לפחות שורה אחת באולם.")
    @Max(value = 16, message="כמות שורות באולם לא תעלה על 15.")
    private Integer numOfRows;
    @Min(value = 1, message = "נדרש לפחות עמודה אחת באולם.")
    @Max(value = 16, message="כמות עמודות באולם לא תעלה על 15.")
    private Integer numOfColumns;
}