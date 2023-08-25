package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Hall.HallCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Hall.HallUpdateDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.services.HallServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/hall")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class HallController {
    private final HallServiceImpl hallServiceImpl;

    @GetMapping("/branch/{id}")
    public List<HallDTO> getHallsByBranchId(@PathVariable("id") Long id) {
        return hallServiceImpl.getHallsByBranchId(id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HallDTO> createHall(@Valid @RequestBody HallCreationDTO hallCreationDTO){
        HallDTO addedHall = hallServiceImpl.createHall(hallCreationDTO);
        return new ResponseEntity<>(addedHall, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDTO> getHallById(@PathVariable("id") Long id) {
        HallDTO hall = hallServiceImpl.getHallById(id);
        if(hall != null) {
            return ResponseEntity.ok(hall);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        if (hallServiceImpl.deleteHall(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HallDTO> updateHall(@PathVariable Long id, @Valid @RequestBody HallUpdateDTO hallUpdateDTO) {
        try {
            HallDTO hall = hallServiceImpl.updateHall(id, hallUpdateDTO);
            if (hall != null) {
                return new ResponseEntity<>(hall, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (org.springframework.dao.DataIntegrityViolationException e){ // Update transaction we check here and not in the service layer since springs functionality
            throw new UniqueException("אולם בשם זה בסניף הזה כבר קיים.");
        }
    }
}
