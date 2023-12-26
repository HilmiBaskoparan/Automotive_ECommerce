package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.responses.addressResponses.FindByCountryIdCityResponse;
import com.hilmibaskoparan.model.entity.City;

import java.util.List;

public interface CityService {

    List<FindByCountryIdCityResponse> findByCountryId(int countryId);

    City findById(int cityId);

}
