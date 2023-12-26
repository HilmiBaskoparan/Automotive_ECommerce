package com.hilmibaskoparan.business.requests.creditCardRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@NoArgsConstructor
public class CreateCreditCartRequest {

    @NotBlank
    private String cardHoldername;

    @NotBlank
    @CreditCardNumber
    private String cardNumber;

    @NotBlank
    private String expireMonth;

    @NotBlank
    private String expireYear;

    @NotNull
    private int customerId;

}
