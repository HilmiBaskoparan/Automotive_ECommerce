package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.*;
import com.hilmibaskoparan.business.requests.productRequest.AddImageForProductRequest;
import com.hilmibaskoparan.business.requests.productRequest.CreateProductRequest;
import com.hilmibaskoparan.business.responses.PaginatedGenericResponse;
import com.hilmibaskoparan.business.responses.productResponses.*;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final ShoppingCardService shoppingCardService;
    private final OrderItemService orderItemService;
    private final BrandService brandService;
    private final ModelMapperService modelMapperService;

    @Lazy
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService,
                              ImageService imageService, ShoppingCardService shoppingCardService,
                              OrderItemService orderItemService, BrandService brandService, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.shoppingCardService = shoppingCardService;
        this.orderItemService = orderItemService;
        this.brandService = brandService;
        this.modelMapperService = modelMapperService;
    }

    @Transactional
    @Override
    public CreateProductResponse addProduct(CreateProductRequest createProductRequest, MultipartFile file) {

        Category category = modelMapperService.forRequest()
                .map(categoryService.getById(createProductRequest.getCategoryId()), Category.class);

        Brand brand = this.brandService.findById(createProductRequest.getBrandId());

        List<Image> images = new ArrayList<>();

        Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);
        this.productRepository.save(product);
        if (file != null) {
            images.add(imageService.uploadImage(file, product));
        }
        product.setImages(images);
        product.setCategory(category);
        product.setBrand(brand);
        this.productRepository.save(product);

        List<String> urls = Optional.ofNullable(product.getImages()).orElse(Collections.emptyList()).stream()
                .map(image -> image.getUrl()).distinct().collect(Collectors.toList());

        CreateProductResponse createProductResponse = modelMapperService.forResponse().map(product,
                CreateProductResponse.class);
        createProductResponse.setUrl(urls);
        return createProductResponse;
    }

    @Transactional
    @Override
    public AddImageForProductResponse addImageForProduct(AddImageForProductRequest addImageForProductRequest, MultipartFile file) {

        Product product = this.productRepository.findById(addImageForProductRequest.getProductId()).get();

        List<Image> images = product.getImages();
        images.add(this.imageService.uploadImage(file, product));
        product.setImages(images);

        List<String> urls = Optional.ofNullable(product.getImages()).orElse(Collections.emptyList()).stream()
                .map(image -> image.getUrl()).distinct().collect(Collectors.toList());

        this.productRepository.save(product);

        AddImageForProductResponse addImageForProductResponse = modelMapperService.forResponse().map(product,
                AddImageForProductResponse.class);
        addImageForProductResponse.setUrl(urls);

        return addImageForProductResponse;
    }

    @Override
    public PaginatedGenericResponse<GetAllProductResponse> getAll(String keyword, int size, int page, BigDecimal minPrice,
                                                                  BigDecimal maxPrice, Optional<String> sortDirection) {

        Pageable pageable;
        if (sortDirection.isPresent()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.get()), "price");
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Product> pageProduct = this.productRepository.findAll(keyword, minPrice, maxPrice, pageable);

        List<GetAllProductResponse> getAllProductResponses = pageProduct.getContent().stream()
                .map(productItem -> modelMapperService.forResponse().map(productItem, GetAllProductResponse.class))
                .collect(Collectors.toList());

        getAllProductResponses.stream().peek(response -> {
            List<String> urls = pageProduct.getContent().stream().filter(product -> product.getId() == response.getId())
                    .flatMap(product -> product.getImages().stream().map(Image::getUrl)).collect(Collectors.toList());
            response.setUrl(urls);
        }).collect(Collectors.toList());

        return new PaginatedGenericResponse<GetAllProductResponse>(getAllProductResponses, pageProduct.getNumber(),
                pageProduct.getSize(), pageProduct.getTotalElements(), pageProduct.getTotalPages());
    }

    @Override
    public PaginatedGenericResponse<FindByBrandIdProductResponse> findByBrandId(String keyword, int brandId, int size, int page,
                                                                                BigDecimal minPrice, BigDecimal maxPrice, Optional<String> sortDirection) {

        Pageable pageable;
        if (sortDirection.isPresent()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.get()), "price");
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Product> pageProduct = this.productRepository.findByBrandId(keyword, brandId, minPrice, maxPrice,
                pageable);

        List<FindByBrandIdProductResponse> response = pageProduct.getContent().stream().map(
                        prodcutItem -> modelMapperService.forResponse().map(prodcutItem, FindByBrandIdProductResponse.class))
                .collect(Collectors.toList());

        response.stream().peek(item -> {
            List<String> urls = pageProduct.getContent().stream().filter(product -> product.getId() == item.getId())
                    .flatMap(product -> product.getImages().stream().map(Image::getUrl)).collect(Collectors.toList());

            item.setUrl(urls);
        }).collect(Collectors.toList());

        return new PaginatedGenericResponse<FindByBrandIdProductResponse>(response, pageProduct.getNumber(),
                pageProduct.getSize(), pageProduct.getTotalElements(), pageProduct.getTotalPages());
    }

    @Override
    public FindByIdProductResponse findById(int productId) {

        Product product = this.productRepository.findById(productId).get();

        FindByIdProductResponse response = modelMapperService.forResponse().map(product, FindByIdProductResponse.class);

        List<String> imageUrl = product.getImages().stream().map(Image::getUrl).collect(Collectors.toList());

        response.setUrl(imageUrl);

        return response;
    }

    @Override
    public PaginatedGenericResponse<FindByCategoryIdProductResponse> findByCategoryId(String keyword, int categoryId, int size, int page, BigDecimal minPrice, BigDecimal maxPrice, Optional<String> sortDirection) {

        Pageable pageable;
        if (sortDirection.isPresent()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.get()), "price");
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Product> pageProduct = this.productRepository.findByCategoryId(keyword, categoryId, minPrice, maxPrice,
                pageable);

        List<FindByCategoryIdProductResponse> response = pageProduct.getContent().stream().map(
                        prodcutItem -> modelMapperService.forResponse().map(prodcutItem, FindByCategoryIdProductResponse.class))
                .collect(Collectors.toList());

        response.stream().peek(item -> {
            List<String> urls = pageProduct.getContent().stream().filter(product -> product.getId() == item.getId())
                    .flatMap(product -> product.getImages().stream().map(Image::getUrl)).collect(Collectors.toList());

            item.setUrl(urls);
        }).collect(Collectors.toList());

        return new PaginatedGenericResponse<FindByCategoryIdProductResponse>(response, pageProduct.getNumber(),
                pageProduct.getSize(), pageProduct.getTotalElements(), pageProduct.getTotalPages());
    }

    @Override
    public void changeProductQuantity(int shoppingCartId) {

        ShoppingCard shoppingCard = this.shoppingCardService.findById(shoppingCartId);

        List<ShoppingCardItem> shoppingCartItems = shoppingCard.getShoppingCardItems();

        List<Product> products = shoppingCartItems.stream().map(item -> {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            return product;
        }).collect(Collectors.toList());

        productRepository.saveAll(products);
    }

    @Override
    public List<FindTopFiveProductResponse> findTopFiveProduct() {

        List<Integer> productIds = orderItemService.findTopFiveProductIds();
        List<Product> products;
        List<FindTopFiveProductResponse> response;

        if (!productIds.isEmpty()) {

            products = productIds.stream().map(id -> {
                Product product = productRepository.findById(id).get();
                return product;
            }).collect(Collectors.toList());
            response = products.stream().map(
                            productItem -> modelMapperService.forResponse().map(productItem, FindTopFiveProductResponse.class))
                    .collect(Collectors.toList());

            response.stream().peek(item -> {
                List<String> urls = products.stream().filter(product -> product.getId() == item.getId())
                        .flatMap(product -> product.getImages().stream().map(Image::getUrl))
                        .collect(Collectors.toList());

                item.setUrl(urls);
            }).collect(Collectors.toList());

            return response;
        } else {
            products = productRepository.findFirstFive();

            response = products.stream().map(
                            productItem -> modelMapperService.forResponse().map(productItem, FindTopFiveProductResponse.class))
                    .collect(Collectors.toList());

            response.stream().peek(item -> {
                List<String> urls = products.stream().filter(product -> product.getId() == item.getId())
                        .flatMap(product -> product.getImages().stream().map(Image::getUrl))
                        .collect(Collectors.toList());

                item.setUrl(urls);
            }).collect(Collectors.toList());
            return response;
        }
    }

    @Override
    public List<FindNewProductResponse> findNewProduct() {

        List<Product> products = productRepository.findNewFiveProducts();

        List<FindNewProductResponse> response = products.stream()
                .map(productItem -> modelMapperService.forResponse().map(productItem, FindNewProductResponse.class))
                .collect(Collectors.toList());

        response.stream().peek(item -> {
            List<String> urls = products.stream().filter(product -> product.getId() == item.getId())
                    .flatMap(product -> product.getImages().stream().map(Image::getUrl)).collect(Collectors.toList());

            item.setUrl(urls);
        }).collect(Collectors.toList());

        return response;
    }

    @Override
    public List<FindNewProductResponse> findNewOrderFiveProduct() {

        List<Integer> productIds = orderItemService.findNewOrderFiveProductIds();

        List<Product> products = productIds.stream().map(id -> {
            Product product = productRepository.findById(id).get();
            return product;
        }).collect(Collectors.toList());

        List<FindNewProductResponse> response = products.stream()
                .map(productItem -> modelMapperService.forResponse().map(productItem, FindNewProductResponse.class))
                .collect(Collectors.toList());

        response.stream().peek(item -> {
            List<String> urls = products.stream().filter(product -> product.getId() == item.getId())
                    .flatMap(product -> product.getImages().stream().map(Image::getUrl)).collect(Collectors.toList());

            item.setUrl(urls);
        }).collect(Collectors.toList());

        return response;
    }

}
