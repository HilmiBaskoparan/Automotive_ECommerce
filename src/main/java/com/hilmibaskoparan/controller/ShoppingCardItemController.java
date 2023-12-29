package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.ShoppingCardItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCardItemController {

    public final ShoppingCardItemService shoppingCardItemService;

    public ShoppingCardItemController(ShoppingCardItemService shoppingCardItemService) {
        this.shoppingCardItemService = shoppingCardItemService;
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/shopping-cart-items")
    public void deleteShoppingCardItem(@RequestParam int shoppingCardItemId) {
        this.shoppingCardItemService.deleteShoppingCardItem(shoppingCardItemId);
    }
}
