package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.creditCardRequest.CreateCreditCardRequest;
import com.hilmibaskoparan.business.responses.creditCardResponse.CreateCreditCardResponse;
import com.hilmibaskoparan.business.responses.creditCardResponse.FindByCustomerIdCreditCardResponse;
import com.hilmibaskoparan.model.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreateCreditCardResponse createCreditCard(CreateCreditCardRequest createCreditCardRequest);

    List<FindByCustomerIdCreditCardResponse> findByCustomerId(int customerId);

    public CreditCard findById(int creditCartId);

}
