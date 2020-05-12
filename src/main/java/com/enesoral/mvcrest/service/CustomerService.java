package com.enesoral.mvcrest.service;

import com.enesoral.mvcrest.generated.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();
    CustomerDto findCustomerById(Long id);
    CustomerDto saveCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    CustomerDto patchCustomer(Long id, CustomerDto customerDto);
    void deleteCustomerById(Long id);
}
