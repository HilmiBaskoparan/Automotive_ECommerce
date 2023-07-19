package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
