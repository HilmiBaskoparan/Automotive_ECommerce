package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.BrandService;
import com.hilmibaskoparan.business.requests.brandRequest.CreateBrandRequest;
import com.hilmibaskoparan.business.responses.brandResponses.CreateBrandResponse;
import com.hilmibaskoparan.business.responses.brandResponses.FindBrandByCategoryIdResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Brand;
import com.hilmibaskoparan.repository.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapperService modelMapperService) {
        this.brandRepository = brandRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<FindBrandByCategoryIdResponse> findByCategoryId(int categoryID) {

        List<Brand> brands = brandRepository.findByCategoriesId(categoryID);

        return brands.stream()
                .map(brand -> modelMapperService.forResponse().map(brand, FindBrandByCategoryIdResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Brand findById(int brandId) {
        return brandRepository.findById(brandId).get();
    }

    @Transactional
    @Override
    public CreateBrandResponse addBrand(CreateBrandRequest createBrandRequest) {

        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandRepository.save(brand);

        CreateBrandResponse response = modelMapperService.forResponse().map(brand, CreateBrandResponse.class);

        return response;
    }
}
