package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.responses.addressResponses.GetAllCountryResponse;

import java.util.List;

public interface CountryService {

    List<GetAllCountryResponse> getAll();

}
