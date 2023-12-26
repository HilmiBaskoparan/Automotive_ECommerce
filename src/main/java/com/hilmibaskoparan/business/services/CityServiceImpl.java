package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CityService;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCountryIdCityResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.City;
import com.hilmibaskoparan.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapperService modelMapperService;

    public CityServiceImpl(CityRepository cityRepository, ModelMapperService modelMapperService) {
        this.cityRepository = cityRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<FindByCountryIdCityResponse> findByCountryId(int countryId) {

        List<City> cities = cityRepository.findByCountryId(countryId);

        List<FindByCountryIdCityResponse> response = cities.stream()
                .map(city -> modelMapperService.forResponse().map(city, FindByCountryIdCityResponse.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public City findById(int cityId) {
        return cityRepository.findById(cityId).get();
    }
}
