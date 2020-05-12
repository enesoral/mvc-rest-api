package com.enesoral.mvcrest.service;

import com.enesoral.mvcrest.api.v1.mapper.CustomerMapper;
import com.enesoral.mvcrest.generated.CustomerDto;
import com.enesoral.mvcrest.controller.v1.CustomerController;
import com.enesoral.mvcrest.domain.Customer;
import com.enesoral.mvcrest.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Enes";

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    Customer customer;
    CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, customerMapper);

        customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);

        customerDto = new CustomerDto();
        customerDto.setFirstName(FIRST_NAME);
    }

    @Test
    void testFindAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDto> customerDtos = customerService.findAllCustomers();

        assertEquals(customers.size(), customerDtos.size());
    }

    @Test
    void testFindCustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDto foundCustomer = customerService.findCustomerById(1L);

        assertEquals(FIRST_NAME, foundCustomer.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/" + customer.getId(), foundCustomer.getCustomerUrl());
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);

        assertEquals(FIRST_NAME, savedCustomer.getFirstName());
        assertEquals(CustomerController.BASE_URL + "/" + customer.getId(), savedCustomer.getCustomerUrl());
    }

    @Test
    void deleteCustomerById() {
        customerRepository.deleteById(ID);

        verify(customerRepository, times(1)).deleteById(ID);
    }
}