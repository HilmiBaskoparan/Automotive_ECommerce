package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.CouponService;
import com.hilmibaskoparan.business.requests.couponRequest.CreateCouponRequest;
import com.hilmibaskoparan.business.requests.couponRequest.UpdateCouponRequest;
import com.hilmibaskoparan.business.responses.PaginatedGenericResponse;
import com.hilmibaskoparan.business.responses.couponResponses.CreateCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.GetAllCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.UpdateCouponResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponsController {

    private final CouponService couponService;

    public CouponsController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCouponResponse addCoupon(@Valid @RequestBody CreateCouponRequest createCouponRequest) {
        return this.couponService.addCoupon(createCouponRequest);
    }

    @GetMapping
    public PaginatedGenericResponse<GetAllCouponResponse> getAllCoupon(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "0") int page) {

        return this.couponService.getAllCoupon(size,page);

    }

    @PutMapping
    public UpdateCouponResponse updateCoupon(@Valid @RequestBody UpdateCouponRequest updateCouponRequest) {
        return this.couponService.updateCoupon(updateCouponRequest);
    }
}
