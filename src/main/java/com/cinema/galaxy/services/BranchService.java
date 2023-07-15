package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    public List<Branch> getBranches(){
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id){
         Optional<Branch> branch = branchRepository.findById(id);
         return branch.orElse(null);
    }

    public Branch createBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public boolean deleteBranch(Long id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
