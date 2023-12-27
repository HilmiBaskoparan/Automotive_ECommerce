package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.ShoppingCardItemService;
import com.hilmibaskoparan.business.abstracts.ShoppingCardService;
import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.model.entity.ShoppingCardItem;
import com.hilmibaskoparan.repository.ShoppingCardItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCardItemServiceImpl implements ShoppingCardItemService {

    private final ShoppingCardItemRepository shoppingCardItemRepository;
    private final ShoppingCardService shoppingCardService;

    @Lazy
    public ShoppingCardItemServiceImpl(ShoppingCardItemRepository shoppingCardItemRepository, ShoppingCardService shoppingCardService) {
        this.shoppingCardItemRepository = shoppingCardItemRepository;
        this.shoppingCardService = shoppingCardService;
    }

    @Transactional
    @Override
    public ShoppingCardItem addShoppingCardItemService(ShoppingCardItem shoppingCardItem) {
        return this.shoppingCardItemRepository.save(shoppingCardItem);
    }

    @Transactional
    @Override
    public ShoppingCardItem updateShoppingCardItemService(ShoppingCardItem shoppingCardItem) {
        return this.shoppingCardItemRepository.save(shoppingCardItem);
    }

    @Override
    public List<ShoppingCardItem> findByCustomerId(int customerId) {
        return this.shoppingCardItemRepository.findByShoppingCardCustomerId(customerId);
    }

    @Override
    public void deleteShoppingCardItem(int shoppingCardItemId) {

        ShoppingCardItem shoppingCardItem = this.shoppingCardItemRepository.findById(shoppingCardItemId).get();

        this.shoppingCardService.updatedShoppingCard(shoppingCardItem.getShoppingCard().getCustomer().getId(),
                shoppingCardItem);

        this.shoppingCardItemRepository.delete(shoppingCardItem);
    }

    @Transactional
    @Override
    public void resetShoppingCardItem(int shoppingCardId) {

        ShoppingCard shoppingCard = shoppingCardService.findById(shoppingCardId);
        shoppingCardService.updatedShoppingCard(shoppingCard);
        shoppingCardItemRepository.deleteAll(shoppingCard.getShoppingCardItems());
    }
}
