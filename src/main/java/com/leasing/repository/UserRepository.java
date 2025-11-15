package com.leasing.repository;

import com.leasing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(User.Role role);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
