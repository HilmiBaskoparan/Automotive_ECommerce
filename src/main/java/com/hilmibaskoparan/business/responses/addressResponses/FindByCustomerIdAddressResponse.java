package com.hilmibaskoparan.business.responses.addressResponses;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FindByCustomerIdAddressResponse {
	
	private int id;

	private String cityName;

//	private String districtName;

	private String postalCode;

	private String fullAddress;
	
	private String addressTitle;

	private String phoneNumber;

	private String identityNumber;
}
