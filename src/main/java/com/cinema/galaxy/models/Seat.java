package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Hall hallId;
    @NotNull
    @Min(value = 1, message = "מספר שורה של מושב חייב להיות גדול מ0.")
    private Integer rowNum;
    @NotNull
    @Min(value = 1, message = "מספר עמודה של מושב חייב להיות גדול מ0.")
    private Integer colNum;

    public Seat(Hall hallId, int rowNum, int colNum) {
        this.hallId = hallId;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", hallId=" + hallId +
                ", rowNum=" + rowNum +
                ", colNum=" + colNum +
                '}';
    }
}
