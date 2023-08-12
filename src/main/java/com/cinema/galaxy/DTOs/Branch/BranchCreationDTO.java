package com.cinema.galaxy.DTOs.Branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BranchCreationDTO {
    @NotBlank(message = "שם סניף נדרש.")
    @Size(min = 2, max = 50, message = "שם סניף חייב להיות באורך של 2-50 תווים.")
    private String name;
    @NotBlank(message = "שם עיר של סניף נדרש.")
    @Size(min = 2, max = 50, message = "שם עיר חייב להיות באורך של 2-50 תווים.")
    private String city;
    @NotBlank(message = "כתובת סניף נדרשת.")
    @Size(min = 2, max = 150, message = "כתובת חייבת להיות באורך של 2-150 תווים.")
    private String address;
    @NotBlank(message = "איש קשר של הסניף נדרש.")
    @Size(min = 2, max = 50, message = "שם איש קשר חייב להיות באורך של 2-50 תווים.")
    private String contactInfo;
}
