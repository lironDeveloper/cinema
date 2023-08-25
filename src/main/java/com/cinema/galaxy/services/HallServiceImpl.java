package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Hall.HallCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Hall.HallUpdateDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.models.Hall;
import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.repositories.BranchRepository;
import com.cinema.galaxy.repositories.HallRepository;
import com.cinema.galaxy.repositories.SeatRepository;
import com.cinema.galaxy.serviceInterfaces.HallService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    private final BranchRepository branchRepository;
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<HallDTO> getHallsByBranchId(Long branchId) {
        List<Hall> halls = hallRepository.findAllByBranchId(branchId);
        return halls.stream().map(hall -> modelMapper.map(hall, HallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HallDTO createHall(HallCreationDTO hallCreationDTO) {
        // Check if the branch with the provided branchId exists in the database
        Branch branch = branchRepository.findById(hallCreationDTO.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים סניף עם המזהה הזה."));

        Hall hall = modelMapper.map(hallCreationDTO, Hall.class);
        hall.setBranch(branch);

        try {
            // Save the hall to the database
            Hall savedHall = hallRepository.save(hall);

            // Create new seats
            List<Seat> seats = createSeatsForHall(savedHall);
            seatRepository.saveAll(seats);

            // Update the hall in the database with the associated seats
            savedHall = hallRepository.save(savedHall);

            return modelMapper.map(savedHall, HallDTO.class);
        } catch (Exception exception){
                throw new UniqueException(String.format("אולם בשם זה בסניף %s כבר קיים.", branch.getName()));
        }
    }

    @Override
    public HallDTO getHallById(Long hallId) {
        Optional<Hall> hall = hallRepository.findById(hallId);
        return modelMapper.map(hall.orElse(null), HallDTO.class);
    }

    @Override
    @Transactional
    public HallDTO updateHall(Long id, HallUpdateDTO hallUpdateDTO) {
        Optional<Hall> hallOptional = hallRepository.findById(id);
        if (hallOptional.isPresent()) {
            Hall hall = hallOptional.get();

            // Save old
            int oldNumOfRows = hall.getNumOfRows(), oldNumOfColumns = hall.getNumOfColumns();

            modelMapper.map(hallUpdateDTO, hall);

            // Try save the hall with the updated data
            hallRepository.save(hall);

            if (oldNumOfColumns != hall.getNumOfColumns() ||
                oldNumOfRows != hall.getNumOfRows()) {
                // Delete all existing seats associated with the hall
                seatRepository.deleteAllByHallId(id);

                // Get new seats with the updated capacity
                List<Seat> newSeats = createSeatsForHall(hall);

                // If seat already exist, there is no need to recreate it
                for(Seat seat: newSeats){
                    if(seatRepository.findSeatByColNumAndRowNumAndHallId(
                            seat.getColNum(),
                            seat.getRowNum(),
                            seat.getHall().getId()
                    ).isEmpty()){
                        seatRepository.save(seat);
                    }
                }
            }
                Hall editedHall = hallRepository.save(hall);
                return modelMapper.map(editedHall, HallDTO.class);

        }
        return null;
    }

    @Override
    public boolean deleteHall(Long hallId) {
        if (hallRepository.existsById(hallId)) {
            hallRepository.deleteById(hallId);
            return true;
        }
        return false;
    }

    private List<Seat> createSeatsForHall(Hall hall) {
        int hallWidth = hall.getNumOfRows();
        int hallHeight = hall.getNumOfColumns();

        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= hallHeight; row++) {
            for (int col = 1; col <= hallWidth; col++) {
                Seat seat = new Seat();
                seat.setHall(hall);
                seat.setRowNum(row);
                seat.setColNum(col);

                // Add the seat to the list
                seats.add(seat);
            }
        }

        return seats;
    }
}
