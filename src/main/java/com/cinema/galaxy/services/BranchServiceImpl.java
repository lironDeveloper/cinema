package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Branch.BranchCreationDTO;
import com.cinema.galaxy.DTOs.Branch.BranchDTO;
import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.repositories.BranchRepository;
import com.cinema.galaxy.serviceInterfaces.BranchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public BranchDTO createBranch(BranchCreationDTO branchCreationDTO){
        Branch branch = modelMapper.map(branchCreationDTO, Branch.class);
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
