package com.eCommerce.userservice.service;

import com.eCommerce.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> updateUser(Long id, User user);
    String deleteUser(Long id);
}
