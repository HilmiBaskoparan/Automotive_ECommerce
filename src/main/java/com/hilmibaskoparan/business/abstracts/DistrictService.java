package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.responses.addressResponses.FindByCityIdDistrictResponse;

import java.util.List;

public interface DistrictService {

    List<FindByCityIdDistrictResponse> findByCityId(int cityId);

}
