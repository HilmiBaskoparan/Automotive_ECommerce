package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.model.entity.ShoppingCardItem;

import java.util.List;

public interface ShoppingCardItemService {

    public ShoppingCardItem addShoppingCartItemService(ShoppingCardItem shoppingCardItem);

    public ShoppingCardItem updateShoppingCartItemService(ShoppingCardItem shoppingCardItem);

    public List<ShoppingCard> findByCustomerId(int customerId);

    public void deleteShoppingCartItem(int shoppingCartItemId);

    public void resetShoppingCartItem(int shoppingCartId);

}
