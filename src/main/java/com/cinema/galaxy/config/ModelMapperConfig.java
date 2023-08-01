package com.cinema.galaxy.config;

import com.cinema.galaxy.DTOs.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.UserDTO;
import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.models.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<User, String> userToUserDTOConverter = context -> {
            User source = context.getSource();
            return source == null ? null : source.getFirstName() + " " + source.getLastName();
        };

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            protected void configure(){
                using(userToUserDTOConverter).map(source).setFullName(null);
            }
        });

        return modelMapper;
    }
}

