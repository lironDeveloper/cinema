package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.User.UserCreationDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.models.User;
import com.cinema.galaxy.repositories.UserRepository;
import com.cinema.galaxy.serviceInterfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserCreationDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        try {
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDTO.class);
        } catch (Exception exception){
            throw new UniqueException("משתמש עם אימייל זה כבר קיים.");
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent()? modelMapper.map(user, UserDTO.class): null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent()? modelMapper.map(user, UserDTO.class): null;

    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
