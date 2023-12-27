package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CountryService;
import com.hilmibaskoparan.business.responses.addressResponses.GetAllCountryResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Country;
import com.hilmibaskoparan.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapperService modelMapperService;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapperService modelMapperService) {
        this.countryRepository = countryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<GetAllCountryResponse> getAll() {

        List<Country> countries = countryRepository.findAll();

        List<GetAllCountryResponse> response = countries.stream().map(country -> modelMapperService
                .forResponse().map(country, GetAllCountryResponse.class)).collect(Collectors.toList());

        return response;
    }
}
