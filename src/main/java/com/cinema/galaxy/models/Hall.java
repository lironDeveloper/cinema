package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "מזהה סניף נדרש..")
    @ManyToOne
    private Branch branch_id;

    @NotBlank(message = "שם האולם נדרש.")
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;

    @NotBlank(message = "כמות מקסימלית של אנשים באולם נדרשת.")
    @Min(value = 1, message = "נדרש לפחות מושב אחד באולם.")
    private int capacity;

    public Hall() {
    }

    public Hall(Branch branch_id, String name, int capacity) {
        this.branch_id = branch_id;
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Branch branch_id) {
        this.branch_id = branch_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", branch_id=" + branch_id +
                ", name='" + name + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
