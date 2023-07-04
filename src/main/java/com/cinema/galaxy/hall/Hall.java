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

    private String capacity;


    public Hall() {
    }

    public Hall(Branch branch_id, String name, String capacity) {
        this.branch_id = branch_id;
        this.name = name;
        this.capacity = capacity;
    }
}
