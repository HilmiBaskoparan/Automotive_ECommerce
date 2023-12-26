package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CustomerService;
import com.hilmibaskoparan.model.entity.Customer;
import com.hilmibaskoparan.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(int customerId) {
        return customerRepository.findById(customerId).get();
    }
}
