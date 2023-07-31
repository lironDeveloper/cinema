package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.BranchDTO;
import com.cinema.galaxy.DTOs.UserDTO;
import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.models.User;
import com.cinema.galaxy.repositories.BranchRepository;
import com.cinema.galaxy.serviceInterfaces.BranchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BranchDTO> getBranches(){
        List<Branch> branches = branchRepository.findAll();
        return branches.stream().map(branch -> modelMapper.map(branch, BranchDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id){
         Optional<Branch> branch = branchRepository.findById(id);
         return modelMapper.map(branch.orElse(null), BranchDTO.class);
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO){
        Branch branch = modelMapper.map(branchDTO, Branch.class);
        Branch savedBranch = branchRepository.save(branch);
        return modelMapper.map(savedBranch, BranchDTO.class);
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
