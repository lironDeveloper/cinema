package com.cinema.galaxy.config;

import com.cinema.galaxy.DTOs.Branch.BranchCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallUpdateDTO;
import com.cinema.galaxy.DTOs.Movie.MovieCreationDTO;
import com.cinema.galaxy.DTOs.Movie.MovieUpdateDTO;
import com.cinema.galaxy.DTOs.Review.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeUpdateDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketReservationDTO;
import com.cinema.galaxy.DTOs.User.UserCreationDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.models.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() { // TODO: improve!!
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Converter<User, String> userToUserDTOConverter = context -> {
            User source = context.getSource();
            return source == null ? null : source.getFirstName() + " " + source.getLastName();
        };

        PropertyMap<BranchCreationDTO, Branch> branchCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<HallCreationDTO, Hall> hallCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<HallUpdateDTO, Hall> hallUpdateMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<MovieCreationDTO, Movie> movieCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<MovieUpdateDTO, Movie> movieUpdateMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<ReviewCreationDTO, Review> reviewCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<ShowtimeCreationDTO, Showtime> showtimeCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<ShowtimeUpdateDTO, Showtime> showtimeUpdateMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<TicketReservationDTO, Ticket> ticketReservationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        PropertyMap<UserCreationDTO, User> userCreationMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
            }
        };

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            protected void configure(){
                using(userToUserDTOConverter).map(source).setFullName(null);
            }
        });
        modelMapper.addMappings(branchCreationMap);
        modelMapper.addMappings(hallCreationMap);
        modelMapper.addMappings(hallUpdateMap);
        modelMapper.addMappings(movieCreationMap);
        modelMapper.addMappings(movieUpdateMap);
        modelMapper.addMappings(reviewCreationMap);
        modelMapper.addMappings(showtimeCreationMap);
        modelMapper.addMappings(showtimeUpdateMap);
        modelMapper.addMappings(ticketReservationMap);
        modelMapper.addMappings(userCreationMap);

        return modelMapper;
    }
}

