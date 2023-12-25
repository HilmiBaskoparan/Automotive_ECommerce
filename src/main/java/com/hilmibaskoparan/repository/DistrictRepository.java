package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findByCityId(int cityId);
}
