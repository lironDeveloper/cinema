package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.models.Branch;

import java.util.List;

public interface BranchService {
    public List<Branch> getBranches();
    public Branch getBranchById(Long id);
    public Branch createBranch(Branch branch);
    public boolean deleteBranch(Long id);
}
