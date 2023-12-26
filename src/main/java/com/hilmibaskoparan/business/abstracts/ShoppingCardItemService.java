package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.model.entity.ShoppingCardItem;

import java.util.List;

public interface ShoppingCardItemService {

    public ShoppingCardItem addShoppingCardItemService(ShoppingCardItem shoppingCardItem);

    public ShoppingCardItem updateShoppingCardItemService(ShoppingCardItem shoppingCardItem);

    public List<ShoppingCard> findByCustomerId(int customerId);

    public void deleteShoppingCardItem(int shoppingCardItemId);

    public void resetShoppingCardItem(int shoppingCardId);

}
