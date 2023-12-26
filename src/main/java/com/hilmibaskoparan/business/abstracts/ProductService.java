package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.productRequest.CreateProductRequest;
import com.hilmibaskoparan.business.responses.PaginatedGenericResponse;
import com.hilmibaskoparan.business.responses.productResponses.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    public CreateProductResponse addProduct(CreateProductRequest createProductRequest, MultipartFile file);

    public PaginatedGenericResponse<GetAllProductResponse> getAll(String keyword, int size, int page,
                                                                  BigDecimal minPrice, BigDecimal maxPrice, Optional<String> sortDirection);

    public PaginatedGenericResponse<FindByBrandIdProductResponse> findByBrandId(String keyword, int brandId, int size, int page ,
                                                                                BigDecimal minPrice, BigDecimal maxPrice, Optional<String> sortDirection);

    public FindByIdProductResponse findById(int productId);


    public PaginatedGenericResponse<FindByCategoryIdProductResponse> findByCategoryId(String keyword, int categoryId, int size,int page,
                                                                                      BigDecimal minPrice, BigDecimal maxPrice,  Optional<String> sortDirection);

    public void changeProductQuantity(int shoppingCartId);

    public List<FindTopFiveProductResponse> findTopFiveProduct();

    public List<FindNewProductResponse> findNewProduct();

    public List<FindNewProductResponse> findNewOrderFiveProduct();
}
