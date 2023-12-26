package com.hilmibaskoparan.business.responses.creditCardResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindByCustomerIdCreditCardResponse {

	private int id;
	
	private String cardHolderName;

	private String cardNumber;

	private String expireMonth;

	private String expireYear;
}
