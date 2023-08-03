package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Seat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    void deleteAllByHallId(Long hallId);

    Optional<Seat> findSeatByColNumAndRowNumAndHallId(
            @NotNull @Min(value = 1, message = "מספר עמודה של מושב חייב להיות גדול מ0.") Integer colNum,
            @NotNull @Min(value = 1, message = "מספר שורה של מושב חייב להיות גדול מ0.") Integer rowNum,
            Long hallId
    );
}
