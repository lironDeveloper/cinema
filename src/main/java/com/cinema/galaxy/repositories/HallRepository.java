package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findAllByBranchId(Long branchId);
}
