package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.UserCreationDTO;
import com.cinema.galaxy.DTOs.UserDTO;
import com.cinema.galaxy.models.User;
import com.cinema.galaxy.repositories.UserRepository;
import com.cinema.galaxy.serviceInterfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserCreationDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return modelMapper.map(user.orElse(null), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
