package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.couponRequest.CouponApplyRequest;
import com.hilmibaskoparan.business.requests.shoppingCardRequest.CreateShoppingCardRequest;
import com.hilmibaskoparan.business.responses.couponResponses.CouponApplyResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.CreateShoppingCardResponse;
import com.hilmibaskoparan.business.responses.shoppingCardResponses.FindByCustomerIdShoppingCardResponse;
import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.model.entity.ShoppingCardItem;

public interface ShoppingCardService {

    public CreateShoppingCardResponse addShoppingCard(CreateShoppingCardRequest createShoppingCardRequest);

    public CouponApplyResponse applyCouponForShoppingCard(CouponApplyRequest couponApplyRequest);

    public FindByCustomerIdShoppingCardResponse findByCustomerId(int customerId);

    public ShoppingCard findById(int id);

    public void updatedShoppingCard(int customerId, ShoppingCardItem shoppingCardItem);

    public void updatedShoppingCard(ShoppingCard shoppingCard);
}
