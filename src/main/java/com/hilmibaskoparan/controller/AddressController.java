package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.AddressService;
import com.hilmibaskoparan.business.requests.addressRequest.CreateAddressRequest;
import com.hilmibaskoparan.business.responses.addressResponses.CreateAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCustomerIdAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByIdAddressResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/address")
    public CreateAddressResponse addAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.addAddress(createAddressRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/address")
    public void deleteAddress(@RequestParam int id) {
        addressService.deleteAddress(id);
    }

    @GetMapping("/address/findById")
    public FindByIdAddressResponse findById(@RequestParam int id) {
        return addressService.findById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/address/findByCustomerId")
    public List<FindByCustomerIdAddressResponse> findByCustomerId(@RequestParam int customerId) {
        return addressService.findByCustomerId(customerId);
    }

}
