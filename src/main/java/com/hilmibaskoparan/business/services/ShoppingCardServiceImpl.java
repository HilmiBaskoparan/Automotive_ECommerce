package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.*;
import com.hilmibaskoparan.business.requests.couponRequest.CouponApplyRequest;
import com.hilmibaskoparan.business.requests.shoppingCardRequest.CreateShoppingCardRequest;
import com.hilmibaskoparan.business.responses.couponResponses.CouponApplyResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.CreateShoppingCardResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.FindByCustomerIdShoppingCardResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.ShoppingCardItemDto;
import com.hilmibaskoparan.business.rules.CouponRules;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.ShoppingCardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final ShoppingCardRepository shoppingCardRepository;
    private final ShoppingCardItemService shoppingCardItemService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CouponService couponService;
    private final CouponRules couponRules;
    private final ModelMapperService modelMapperService;

    public ShoppingCardServiceImpl(ShoppingCardRepository shoppingCardRepository, ShoppingCardItemService shoppingCardItemService,
                                   ProductService productService, CustomerService customerService, CouponService couponService,
                                   CouponRules couponRules, ModelMapperService modelMapperService) {
        this.shoppingCardRepository = shoppingCardRepository;
        this.shoppingCardItemService = shoppingCardItemService;
        this.productService = productService;
        this.customerService = customerService;
        this.couponService = couponService;
        this.couponRules = couponRules;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public CreateShoppingCardResponse addShoppingCard(CreateShoppingCardRequest createShoppingCardRequest) {

        Product product = modelMapperService.forRequest()
                .map(productService.findById(createShoppingCardRequest.getProductId()), Product.class);

        Customer customer = this.customerService.findById(createShoppingCardRequest.getCustomerId());

        List<ShoppingCardItem> shoppingCardItems = this.shoppingCardItemService
                .findByCustomerId(createShoppingCardRequest.getCustomerId());

        ShoppingCard shoppingCard = this.shoppingCardRepository
                .findByCustomerId(createShoppingCardRequest.getCustomerId()).orElse(ShoppingCard.builder()
                        .customer(customer).totalAmount(BigDecimal.ZERO).shoppingCardItems(shoppingCardItems).build());

        this.shoppingCardRepository.save(shoppingCard);

        if (indexOfProduct(shoppingCardItems, product) == -1) {

            ShoppingCardItem shoppingCardItem = ShoppingCardItem.builder().product(product).shoppingCard(shoppingCard)
                    .quantity(createShoppingCardRequest.getQuantity()).build();

            shoppingCardItems.add(shoppingCardItem);
            shoppingCardItemService.addShoppingCardItemService(shoppingCardItem);

        } else {

            ShoppingCardItem shoppingCardItem = shoppingCardItems.get(indexOfProduct(shoppingCardItems, product));
            shoppingCardItem.setQuantity(shoppingCardItem.getQuantity() + createShoppingCardRequest.getQuantity());
            shoppingCardItemService.addShoppingCardItemService(shoppingCardItem);
        }

        shoppingCard.setTotalAmount(calculateTotalAndDiscountedAmount(shoppingCard, null)[0]);
        shoppingCard.setDiscountedTotalAmount(calculateTotalAndDiscountedAmount(shoppingCard, null)[1]);

        this.shoppingCardRepository.save(shoppingCard);
        return modelMapperService.forResponse().map(shoppingCard, CreateShoppingCardResponse.class);
    }

    @Override
    public CouponApplyResponse applyCouponForShoppingCard(CouponApplyRequest couponApplyRequest) {

        ShoppingCard shoppingCard = shoppingCardRepository.findByCustomerId(couponApplyRequest.getCustomerId()).get();

        shoppingCard.setDiscountedTotalAmount(
                calculateTotalAndDiscountedAmount(shoppingCard, couponApplyRequest.getDiscountCode())[1]);

        shoppingCard.setTotalAmount(
                calculateTotalAndDiscountedAmount(shoppingCard, couponApplyRequest.getDiscountCode())[0]);

        return modelMapperService.forResponse().map(shoppingCard, CouponApplyResponse.class);
    }

    @Override
    public FindByCustomerIdShoppingCardResponse findByCustomerId(int customerId) {

        ShoppingCard shoppingCard = shoppingCardRepository.findByCustomerId(customerId).orElse(new ShoppingCard());

        return FindByCustomerIdShoppingCardResponse.builder().id(shoppingCard.getId())
                .totalAmount(shoppingCard.getTotalAmount())
                .discountedTotalAmount(shoppingCard.getDiscountedTotalAmount())
                .shoppingCardItemDtos(getTotalPriceForShoppingCardItemDto(shoppingCard.getShoppingCardItems()))

                .build();
    }

    @Override
    public ShoppingCard findById(int id) {
        return shoppingCardRepository.findById(id).get();
    }

    @Override
    public void updatedShoppingCard(int customerId, ShoppingCardItem shoppingCardItem) {

        ShoppingCard shoppingCard = this.shoppingCardRepository.findByCustomerId(customerId).get();

        shoppingCard.setTotalAmount(shoppingCard.getTotalAmount().subtract(
                shoppingCardItem.getProduct().getPrice().multiply(BigDecimal.valueOf(shoppingCardItem.getQuantity()))));
        shoppingCard.setDiscountedTotalAmount(shoppingCard.getDiscountedTotalAmount().subtract(
                shoppingCardItem.getProduct().getPrice().multiply(BigDecimal.valueOf(shoppingCardItem.getQuantity()))));

        this.shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public void updatedShoppingCard(ShoppingCard shoppingCard) {

        shoppingCard.setTotalAmount(new BigDecimal(0));
        shoppingCard.setDiscountedTotalAmount(new BigDecimal(0));

        this.shoppingCardRepository.save(shoppingCard);
    }

    // Index Of Product
    private int indexOfProduct(List<ShoppingCardItem> shoppingCardItems, Product product) {
        for (int i = 0; i < shoppingCardItems.size(); i++) {
            if (shoppingCardItems.get(i).getProduct().equals(product)) {
                return i;
            }
        }
        return -1;
    }

    private BigDecimal[] calculateTotalAndDiscountedAmount(ShoppingCard shoppingCard, String discountCode) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        BigDecimal discountedAmount = BigDecimal.ZERO;
        for (ShoppingCardItem cardItem : shoppingCard.getShoppingCardItems()) {
            totalAmount = totalAmount
                    .add(cardItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cardItem.getQuantity())));

        }

        if (discountCode != null) {
            BigDecimal discountRate = findByDiscountRate(discountCode);
            discountedAmount = totalAmount.subtract(totalAmount.multiply(discountRate).divide(BigDecimal.valueOf(100)));
        } else {
            discountedAmount = totalAmount;
        }

        return new BigDecimal[] { totalAmount, discountedAmount };

    }

    private BigDecimal findByDiscountRate(String code) {

        BigDecimal discountRate;

        Coupon coupon = this.couponService.findByCode(code);

        couponRules.checkCouponDateExpiry(coupon);
        couponRules.checkCouponValidity(code);

        if (coupon.getDiscount() == null) {
            discountRate = BigDecimal.ZERO;
        } else {
            discountRate = coupon.getDiscount();
        }
        return discountRate;

    }

    private List<ShoppingCardItemDto> getTotalPriceForShoppingCardItemDto(List<ShoppingCardItem> shoppingCardItems) {

        if(shoppingCardItems == null) {
            return new ArrayList<>();
        }

        List<ShoppingCardItemDto> shoppingCardItemDtos = shoppingCardItems.stream()
                .map(item -> {
                    ShoppingCardItemDto dto = modelMapperService.forResponse().map(item, ShoppingCardItemDto.class);

                    Product product = item.getProduct();
                    if (product != null) {
                        List<String> urls = product.getImages().stream()
                                .map(Image::getUrl)
                                .collect(Collectors.toList());
                        dto.setUrls(urls);
                    }

                    return dto;
                })
                .collect(Collectors.toList());


        if (shoppingCardItemDtos != null) {
            shoppingCardItemDtos.stream().forEach(
                    item -> item.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
        }

        return shoppingCardItemDtos;

    }

}
