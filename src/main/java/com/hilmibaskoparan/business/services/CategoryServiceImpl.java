package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.BrandService;
import com.hilmibaskoparan.business.abstracts.CategoryService;
import com.hilmibaskoparan.business.requests.categoryRequest.CreateCategoryRequest;
import com.hilmibaskoparan.business.responses.categoryResponses.CreateCategoryResponse;
import com.hilmibaskoparan.business.responses.categoryResponses.GetAllCategoryResponse;
import com.hilmibaskoparan.business.responses.categoryResponses.GetByIdCategoryResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Brand;
import com.hilmibaskoparan.model.entity.Category;
import com.hilmibaskoparan.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;
    private final BrandService brandService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapperService modelMapperService, BrandService brandService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
        this.brandService = brandService;
    }

    @Transactional
    @Override
    public CreateCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest) {

        Brand brand = brandService.findById(createCategoryRequest.getBrandId());

        Category category = modelMapperService.forRequest().map(createCategoryRequest, Category.class);
        category.setId(0);
        brand.getCategories().add(category);
        category.getBrands().add(brand);

        categoryRepository.save(category);

        CreateCategoryResponse response = modelMapperService.forResponse().map(category, CreateCategoryResponse.class);
        response.setBrandName(brand.getName());
        return response;
    }

    @Override
    public List<GetAllCategoryResponse> getAll() {

        List<Category> categories = this.categoryRepository.findAll();

        List<GetAllCategoryResponse> getAllCategoryResponses = categories.stream()
                .map(category -> modelMapperService.forResponse().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());

        return getAllCategoryResponses;
    }

    @Override
    public GetByIdCategoryResponse getById(int categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElse(null);

        return modelMapperService.forResponse().map(category, GetByIdCategoryResponse.class);
    }
}
