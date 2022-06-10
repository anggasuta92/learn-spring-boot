package com.test.demo.service;

import java.util.List;
import java.util.Optional;
import com.test.demo.model.User;

public interface UserService {
    
    List<User> getAll();

    Optional<User> getUserById(Long id);

    User saveUser(User user);
}
