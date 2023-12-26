package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.couponRequest.CreateCouponRequest;
import com.hilmibaskoparan.business.requests.couponRequest.UpdateCouponRequest;
import com.hilmibaskoparan.business.responses.PaginatedGenericResponse;
import com.hilmibaskoparan.business.responses.couponResponses.CreateCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.GetAllCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.UpdateCouponResponse;
import com.hilmibaskoparan.model.entity.Coupon;

public interface CouponService {

    public CreateCouponResponse addCoupon(CreateCouponRequest createCouponRequest);

    public UpdateCouponResponse updateCoupon(UpdateCouponRequest updateCouponRequest);

    public PaginatedGenericResponse<GetAllCouponResponse> getAllCoupon(int size, int page);

    public Coupon findByCode(String code);

}
