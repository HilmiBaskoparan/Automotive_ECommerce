package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.couponRequest.CouponApplyRequest;
import com.hilmibaskoparan.business.requests.shoppingCardRequest.CreateShoppingCartRequest;
import com.hilmibaskoparan.business.responses.couponResponses.CouponApplyResponse;
import com.hilmibaskoparan.business.responses.shoppingCartResponses.CreateShoppingCartResponse;
import com.hilmibaskoparan.business.responses.shoppingCartResponses.FindByCustomerIdShoppingCartResponse;
import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.model.entity.ShoppingCardItem;

public interface ShoppingCardService {

    public CreateShoppingCartResponse addShoppingCart(CreateShoppingCartRequest createShoppingCartRequest);

    public CouponApplyResponse applyCouponForShoppingCart(CouponApplyRequest couponApplyRequest);

    public FindByCustomerIdShoppingCartResponse findByCustomerId(int customerId);

    public ShoppingCard findById(int id);

    public void updatedShoppingCart(int customerId, ShoppingCardItem shoppingCardItem);

    public void updatedShoppingCart(ShoppingCard shoppingCard);
}
