package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.addressRequest.CreateAddressRequest;
import com.hilmibaskoparan.business.responses.addressResponses.CreateAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCustomerIdAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByIdAddressResponse;

import java.util.List;

public interface AddressService {

    CreateAddressResponse addAddress(CreateAddressRequest createAddressRequest);

    List<FindByCustomerIdAddressResponse> findByCustomerId(int customerId);

    FindByIdAddressResponse findById(int id);

    void deleteAddress(int id);

}
