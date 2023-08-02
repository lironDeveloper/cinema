package com.cinema.galaxy.DTOs.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreationDTO {
    private Long id;
    @NotNull(message = "יש לציין מזהה סרט.")
    private Long movieId;
    @NotNull(message = "יש לציין דירוג.")
    @Range(min = 1, max = 5, message = "דירוג הביקורת הוא מספר בין 1 ל5.")
    private Integer rating;
    @NotBlank(message = "תגובה לביקורת נדרשת.")
    @Size(min = 5, max = 255, message = "תגובה לביקורת חייבת להיות באורך של 5 עד 255 תווים.")
    private String comment;
}
