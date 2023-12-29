package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.ShoppingCardService;
import com.hilmibaskoparan.business.requests.couponRequest.CouponApplyRequest;
import com.hilmibaskoparan.business.requests.shoppingCardRequest.CreateShoppingCardRequest;
import com.hilmibaskoparan.business.responses.couponResponses.CouponApplyResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.CreateShoppingCardResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.FindByCustomerIdShoppingCardResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCardController {

    public final ShoppingCardService shoppingCardService;

    public ShoppingCardController(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/shopping-carts")
    public CreateShoppingCardResponse addShoppingCard(@Valid @RequestBody CreateShoppingCardRequest createShoppingCardRequest) {
        return this.shoppingCardService.addShoppingCard(createShoppingCardRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/shopping-carts")
    public FindByCustomerIdShoppingCardResponse findByCustomerId(@RequestParam int customerId){
        return this.shoppingCardService.findByCustomerId(customerId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply-shopping-carts")
    public CouponApplyResponse addShoppingCard(@Valid @RequestBody CouponApplyRequest couponApplyRequest) {
        return this.shoppingCardService.applyCouponForShoppingCard(couponApplyRequest);
    }
}
