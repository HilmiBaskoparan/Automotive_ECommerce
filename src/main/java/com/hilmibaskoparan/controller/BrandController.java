package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.BrandService;
import com.hilmibaskoparan.business.requests.brandRequest.CreateBrandRequest;
import com.hilmibaskoparan.business.responses.brandResponses.CreateBrandResponse;
import com.hilmibaskoparan.business.responses.brandResponses.FindBrandByCategoryIdResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/findByCategoryId")
    public List<FindBrandByCategoryIdResponse> findByCategoryId(@RequestParam int categoryId) {
        return brandService.findByCategoryId(categoryId);

    };

    @PostMapping("/brands")
    public CreateBrandResponse addBrand(@RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.addBrand(createBrandRequest);
    }
}
