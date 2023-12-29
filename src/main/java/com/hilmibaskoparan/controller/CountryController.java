package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.CountryService;
import com.hilmibaskoparan.business.responses.addressResponses.GetAllCountryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<GetAllCountryResponse> getAll() {
        return countryService.getAll();
    }
}
