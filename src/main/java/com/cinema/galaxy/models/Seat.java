package com.cinema.galaxy.models;

import com.cinema.galaxy.models.Hall;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotBlank(message = "מזהה אולם נדרש.")
    @Size(min = 1, max = 255, message = "מזהה אולם חייב להיות באורך של 1 עד 255 תווים.")
    private Hall hall_id;
    @NotBlank(message = "שורה של מושב נדרשת.")
    @Min(value = 1, message = "מספר שורה של מושב חייב להיות גדול מ0.")
    private int row_num;
    @NotBlank(message = "עמודה של מושב נדרשת.")
    @Min(value = 1, message = "מספר עמודה של מושב חייב להיות גדול מ0.")
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
