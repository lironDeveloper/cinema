package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.repositories.BranchRepository;
import com.cinema.galaxy.serviceInterfaces.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<Branch> getBranches(){
        return branchRepository.findAll();
    }

    @Override
    public Branch getBranchById(Long id){
         Optional<Branch> branch = branchRepository.findById(id);
         return branch.orElse(null);
    }

    @Override
    public Branch createBranch(Branch branch){
        return branchRepository.save(branch);
    }

    @Override
    public boolean deleteBranch(Long id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
