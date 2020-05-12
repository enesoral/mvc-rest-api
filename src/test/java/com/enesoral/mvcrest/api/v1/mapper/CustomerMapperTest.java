package com.enesoral.mvcrest.api.v1.mapper;

import com.enesoral.mvcrest.generated.CustomerDto;
import com.enesoral.mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {

    private static final String FIRST_NAME = "Enes";
    private static final String LAST_NAME = "Oral";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDtoTest() {
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);

        assertEquals(FIRST_NAME, customerDto.getFirstName());
        assertEquals(LAST_NAME, customerDto.getLastName());
    }

    @Test
    public void customerDtoToCustomerTest() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(FIRST_NAME);
        customerDto.setLastName(LAST_NAME);

        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
    }
}
