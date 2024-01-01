package com.hilmibaskoparan.validator.impl;

import com.hilmibaskoparan.repository.UserRepository;
import com.hilmibaskoparan.validator.UniqueUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserNameImpl implements ConstraintValidator<UniqueUserName, String> {

    private final UserRepository userRepository;

    public UniqueUserNameImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(userName);
    }

}