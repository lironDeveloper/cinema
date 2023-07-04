package com.cinema.galaxy.branch;

import com.cinema.galaxy.user.User;
import com.cinema.galaxy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
