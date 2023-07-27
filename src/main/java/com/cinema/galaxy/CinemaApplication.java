package com.cinema.galaxy;

import com.cinema.galaxy.DTOs.ReviewCreationDTO;
import com.cinema.galaxy.models.Review;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CinemaApplication {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.addMappings(new PropertyMap<Review, ReviewCreationDTO>() {
//			@Override
//			protected void configure() {
//				map().setMovieId(source.getMovie().getId());
//				map().setUserId(source.getUser().getId());
//			}
//		});

		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
}
