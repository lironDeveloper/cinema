package com.cinema.galaxy.DTOs.Movie;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MovieUpdateDTO {
    @Size(min = 2, max = 100, message = "שם סרט חייב להיות באורך של 2-100 תווים.")
    private String title;
    @Size(min = 2, max = 254, message = "תיאור סרט חייב להיות באורך של 2-254 תווים.")
    private String description;
    @Range(min = 1, max=300, message = "אורך סרט חייב להיות בין דקה ל5 שעות.")
    private Integer duration;
    @PastOrPresent(message = "תאריך הוצאת סרט חייב להיות בעבר.")
    private LocalDateTime releaseDate;
    @ValidEnumValue(enumClass = Genre.class, message = "יש לבחור זאנ'ר חוקי.")
    private String genre;
    @Size(min = 2, max = 100, message = "שם במאי של הסרט חייב להיות באורך של 2-100 תווים.")
    private String director;
    @ValidEnumValue(enumClass = Language.class, message = "יש לבחור שפה חוקית.")
    private String language;
    @Range(min = 0, max=18, message = "גיל מינימאלי חייב להיות בין 0 ל18.")
    private Integer minAge;
}
