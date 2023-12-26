package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.Customer;

public interface CustomerService {

    public Customer findById(int customerId);

}
