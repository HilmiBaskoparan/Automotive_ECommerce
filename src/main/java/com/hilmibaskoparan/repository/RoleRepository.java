package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.ERole;
import com.hilmibaskoparan.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole role);

}
