package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.brandRequest.CreateBrandRequest;
import com.hilmibaskoparan.business.responses.brandResponses.CreateBrandResponse;
import com.hilmibaskoparan.business.responses.brandResponses.FindBrandByCategoryIdResponse;
import com.hilmibaskoparan.model.entity.Brand;

import java.util.List;

public interface BrandService {

    List<FindBrandByCategoryIdResponse> findByCategoryId(int categoryID);

    Brand findById(int brandId);

    CreateBrandResponse addBrand(CreateBrandRequest createBrandRequest);
}
