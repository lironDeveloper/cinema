package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Hall.HallCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Hall.HallUpdateDTO;

import java.util.List;

public interface HallService {
    public List<HallDTO> getHallsByBranchId(Long branchId);
    public HallDTO createHall(HallCreationDTO hall);
    public HallDTO getHallById(Long hallId);
    HallDTO updateHall(Long id, HallUpdateDTO hall);
    public boolean deleteHall(Long hallId);
}
