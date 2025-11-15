package com.leasing.service;

import com.leasing.model.User;

import java.util.Optional;
import java.util.List;

public interface UserService {

    User create(User user, String rawPassword);

    User update(Long id, User user, String rawPassword);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void delete(Long id);

    boolean existsByUsername(String username);
}
