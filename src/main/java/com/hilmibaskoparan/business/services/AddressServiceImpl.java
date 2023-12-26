package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.AddressService;
import com.hilmibaskoparan.business.abstracts.CityService;
import com.hilmibaskoparan.business.abstracts.CustomerService;
import com.hilmibaskoparan.business.requests.addressRequest.CreateAddressRequest;
import com.hilmibaskoparan.business.responses.addressResponses.CreateAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCustomerIdAddressResponse;
import com.hilmibaskoparan.business.responses.addressResponses.FindByIdAddressResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Address;
import com.hilmibaskoparan.model.entity.City;
import com.hilmibaskoparan.model.entity.Customer;
import com.hilmibaskoparan.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerService customerService;
    private final CityService cityService;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapperService modelMapperService, CustomerService customerService, CityService cityService) {
        this.addressRepository = addressRepository;
        this.modelMapperService = modelMapperService;
        this.customerService = customerService;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public CreateAddressResponse addAddress(CreateAddressRequest createAddressRequest) {

        Customer customer = customerService.findById(createAddressRequest.getCustomerId());
        City city = cityService.findById(createAddressRequest.getCityId());

        Address address = modelMapperService.forRequest().map(createAddressRequest, Address.class);
        address.setUser(customer);
        address.setCity(city);

        this.addressRepository.save(address);

        CreateAddressResponse response = modelMapperService.forResponse().map(address, CreateAddressResponse.class);

        response.setDistrictName(address.getCity().getDistricts().stream()
                .filter(item -> item.getId() == createAddressRequest.getDistrictId()).findFirst().get().getName());

        return response;
    }

    @Override
    public List<FindByCustomerIdAddressResponse> findByCustomerId(int customerId) {

        List<Address> addresses = addressRepository.findByUserId(customerId);

        List<FindByCustomerIdAddressResponse> responses = addresses.stream().map(address -> {
            FindByCustomerIdAddressResponse findByCustomerIdAddressResponse = modelMapperService.forResponse()
                    .map(address, FindByCustomerIdAddressResponse.class);
            return findByCustomerIdAddressResponse;
        }).collect(Collectors.toList());

        return responses;
    }

    @Override
    public FindByIdAddressResponse findById(int id) {
        Address address = addressRepository.findById(id).get();
        return modelMapperService.forResponse().map(address, FindByIdAddressResponse.class);
    }

    @Override
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }
}
