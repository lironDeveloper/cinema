package com.cinema.galaxy.DTOs.Hall;

import com.cinema.galaxy.DTOs.Branch.BranchDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class HallDTO {
    private Long id;
    private BranchDTO branch;
    private String name;
    private int numOfRows;
    private int numOfColumns;
    private Instant createdOn;
}
