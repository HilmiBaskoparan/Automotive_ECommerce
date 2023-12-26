package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.categoryRequest.CreateCategoryRequest;
import com.hilmibaskoparan.business.responses.categoryResponses.CreateCategoryResponse;
import com.hilmibaskoparan.business.responses.categoryResponses.GetAllCategoryResponse;
import com.hilmibaskoparan.business.responses.categoryResponses.GetByIdCategoryResponse;

import java.util.List;

public interface CategoryService {

    public CreateCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest);

    public List<GetAllCategoryResponse> getAll();

    public GetByIdCategoryResponse getById(int categoryId);

}
