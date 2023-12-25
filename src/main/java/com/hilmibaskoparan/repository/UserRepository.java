package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String email);

    List<User> findByAccountNonLockedIsFalse();

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

}
