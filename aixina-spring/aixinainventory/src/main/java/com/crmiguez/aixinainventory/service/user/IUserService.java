package com.crmiguez.aixinainventory.service.user;

import com.crmiguez.aixinainventory.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> findAllUsers();
    public Optional<User> findUserById(Long userId);
    public User addUser(User user);
    public User updateUser(User userDetails, User user);
    public void deleteUser (User user);

}
