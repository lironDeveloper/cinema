package com.cinema.galaxy.seat;

import com.cinema.galaxy.hall.Hall;
import jakarta.persistence.*;

@Table
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Hall hall_id;
    private int row_num;
    private int col_num;

    public Seat(Hall hall_id, int row_num, int col_num) {
        this.hall_id = hall_id;
        this.row_num = row_num;
        this.col_num = col_num;
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hall getHall_id() {
        return hall_id;
    }

    public void setHall_id(Hall hall_id) {
        this.hall_id = hall_id;
    }

    public int getRow_num() {
        return row_num;
    }

    public void setRow_num(int row_num) {
        this.row_num = row_num;
    }

    public int getCol_num() {
        return col_num;
    }

    public void setCol_num(int col_num) {
        this.col_num = col_num;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", hall_id=" + hall_id +
                ", row_num=" + row_num +
                ", col_num=" + col_num +
                '}';
    }
}
