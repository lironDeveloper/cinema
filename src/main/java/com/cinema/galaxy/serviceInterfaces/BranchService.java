package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Branch.BranchDTO;

import java.util.List;

public interface BranchService {
    public List<BranchDTO> getBranches();
    public BranchDTO getBranchById(Long id);
    public BranchDTO createBranch(BranchDTO branch);
    public boolean deleteBranch(Long id);
}
