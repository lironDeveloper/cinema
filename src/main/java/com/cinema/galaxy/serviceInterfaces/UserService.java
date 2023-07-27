package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.UserCreationDTO;
import com.cinema.galaxy.DTOs.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO createUser(UserCreationDTO user);
    public UserDTO getUserById(Long userId);
    UserDTO updateUser(UserDTO user);
    public void deleteUser(Long userId);
}
