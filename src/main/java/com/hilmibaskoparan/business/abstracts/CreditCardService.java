package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.creditCardRequest.CreateCreditCartRequest;
import com.hilmibaskoparan.business.responses.creditCardResponse.CreateCreditCartResponse;
import com.hilmibaskoparan.business.responses.creditCardResponse.FindByCustomerIdCreditCardResponse;
import com.hilmibaskoparan.model.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreateCreditCartResponse createCreditCart(CreateCreditCartRequest createCreditCartRequest);

    List<FindByCustomerIdCreditCardResponse> findByCustomerId(int customerId);

    public CreditCard findById(int creditCartId);

}
