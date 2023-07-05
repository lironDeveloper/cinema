package com.cinema.galaxy.hall;

import com.cinema.galaxy.branch.Branch;
import jakarta.persistence.*;

@Table
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Branch branch_id;
    private String name;

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
