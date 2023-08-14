package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.User.UserCreationDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO createUser(UserCreationDTO user);
    public UserDTO getUserById(Long userId);
    public UserDTO getUserByEmail(String email);
    UserDTO updateUser(UserDTO user);
    public void deleteUser(Long userId);
}
