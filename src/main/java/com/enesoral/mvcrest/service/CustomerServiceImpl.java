package com.enesoral.mvcrest.service;

import com.enesoral.mvcrest.api.v1.mapper.CustomerMapper;
import com.enesoral.mvcrest.domain.Customer;
import com.enesoral.mvcrest.generated.CustomerDto;
import com.enesoral.mvcrest.controller.v1.CustomerController;
import com.enesoral.mvcrest.exception.ResourceNotFoundException;
import com.enesoral.mvcrest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl(getCustomerUrl(customer));
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl(getCustomerUrl(customer));
                    return customerDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return save(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerMapper.customerDtoToCustomer(customerDto);
            customer.setId(id);
            return save(customer);
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if (customerDto.getFirstName() != null) {
                        customer.setFirstName(customerDto.getFirstName());
                    }

                    if (customerDto.getLastName() != null) {
                        customer.setLastName(customerDto.getLastName());
                    }

                    CustomerDto updatedCustomer = save(customer);
                    updatedCustomer.setCustomerUrl(getCustomerUrl(customer));
                    return updatedCustomer;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    private String getCustomerUrl(Customer customer) {
        return CustomerController.BASE_URL + "/" + customer.getId();
    }

    private CustomerDto save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDto customerDto = customerMapper.customerToCustomerDto(savedCustomer);
        customerDto.setCustomerUrl(getCustomerUrl(savedCustomer));
        return customerDto;
    }
}
