package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.CityService;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCountryIdCityResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities/findByCountryId")
    public List<FindByCountryIdCityResponse> findByCountryId(@RequestParam int countryId) {
        return cityService.findByCountryId(countryId);
    }
}
