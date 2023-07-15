package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "מזהה סניף נדרש.")
    @ManyToOne
    private Branch branchId;
    @NotBlank(message = "שם האולם נדרש.")
    @Size(min = 2, max = 50, message = "שם אולם חייב להיות באורך של 2-50 תווים.")
    private String name;
    @NotNull
    @Min(value = 1, message = "נדרש לפחות מושב אחד באולם.")
    private Integer capacity;

    public Hall() {
    }

    public Hall(Branch branchId, String name, int capacity) {
        this.branchId = branchId;
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
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
                ", branchId=" + branchId +
                ", name='" + name + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
