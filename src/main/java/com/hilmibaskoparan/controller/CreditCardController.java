package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.CreditCardService;
import com.hilmibaskoparan.business.requests.creditCardRequest.CreateCreditCardRequest;
import com.hilmibaskoparan.business.responses.creditCardResponse.CreateCreditCardResponse;
import com.hilmibaskoparan.business.responses.creditCardResponse.FindByCustomerIdCreditCardResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CreditCardController {

    public final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/creditCard")
    public CreateCreditCardResponse createCreditCard(@RequestBody CreateCreditCardRequest createCreditCardRequest) {
        return creditCardService.createCreditCard(createCreditCardRequest);
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/creditCard")
    public List<FindByCustomerIdCreditCardResponse> findByCustomerId(@RequestParam int customerId) {
        return creditCardService.findByCustomerId(customerId);
    }
}
