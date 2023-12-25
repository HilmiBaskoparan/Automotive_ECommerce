package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
