package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.CategoryService;
import com.hilmibaskoparan.business.requests.categoryRequest.CreateCategoryRequest;
import com.hilmibaskoparan.business.responses.categoryResponses.CreateCategoryResponse;
import com.hilmibaskoparan.business.responses.categoryResponses.GetAllCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<GetAllCategoryResponse> getAllCategories(){
        return categoryService.getAll();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("categories")
    public CreateCategoryResponse addCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest)  {
        return this.categoryService.addCategory(createCategoryRequest);
    }

}
