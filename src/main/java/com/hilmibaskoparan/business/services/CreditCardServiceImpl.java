package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CreditCardService;
import com.hilmibaskoparan.business.abstracts.CustomerService;
import com.hilmibaskoparan.business.requests.creditCardRequest.CreateCreditCardRequest;
import com.hilmibaskoparan.business.responses.creditCardResponse.CreateCreditCardResponse;
import com.hilmibaskoparan.business.responses.creditCardResponse.FindByCustomerIdCreditCardResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.CreditCard;
import com.hilmibaskoparan.model.entity.Customer;
import com.hilmibaskoparan.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerService customerService;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, ModelMapperService modelMapperService, CustomerService customerService) {
        this.creditCardRepository = creditCardRepository;
        this.modelMapperService = modelMapperService;
        this.customerService = customerService;
    }

    @Override
    public CreateCreditCardResponse createCreditCard(CreateCreditCardRequest createCreditCardRequest) {

        Customer customer = customerService.findById(createCreditCardRequest.getCustomerId());

        CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
        creditCard.setId(0);
        creditCard.setUser(customer);

        creditCardRepository.save(creditCard);

        return modelMapperService.forResponse().map(creditCard, CreateCreditCardResponse.class);
    }

    @Override
    public List<FindByCustomerIdCreditCardResponse> findByCustomerId(int customerId) {

        List<CreditCard> creditCards = creditCardRepository.findByUserId(customerId);

        List<FindByCustomerIdCreditCardResponse> responses = creditCards.stream()
                .map(card -> modelMapperService.forResponse().map(card, FindByCustomerIdCreditCardResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public CreditCard findById(int creditCartId) {
        return creditCardRepository.findById(creditCartId).get();
    }
}
