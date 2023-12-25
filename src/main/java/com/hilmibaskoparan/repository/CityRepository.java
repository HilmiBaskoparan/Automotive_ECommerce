package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByCountryId(int countryId);

}
