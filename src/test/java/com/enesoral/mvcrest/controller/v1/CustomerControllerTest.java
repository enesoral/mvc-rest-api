package com.enesoral.mvcrest.controller.v1;

import com.enesoral.mvcrest.api.v1.model.CustomerDto;
import com.enesoral.mvcrest.domain.Customer;
import com.enesoral.mvcrest.exception.ResourceNotFoundException;
import com.enesoral.mvcrest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Enes";
    public static final String LAST_NAME = "Oral";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    Customer customer;
    CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

        loadCustomers();
    }

    private void loadCustomers() {
        customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        customerDto = new CustomerDto();
        customerDto.setFirstName(FIRST_NAME);
        customerDto.setLastName(LAST_NAME);
        customerDto.setCustomerUrl(CustomerController.BASE_URL + "/" + ID);
    }

    @Test
    void testGetCustomersList() throws Exception {
        when(customerService.findAllCustomers()).thenReturn(Arrays.asList(customerDto, customerDto));

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {
        when(customerService.findCustomerById(ID)).thenReturn(customerDto);

        mockMvc.perform(get(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    void testAddCustomer() throws Exception {
        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(this.customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + ID)));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        when(customerService.updateCustomer(anyLong(), any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(put(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(this.customerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + ID)));
    }

    @Test
    void testPatchCustomer() throws Exception {
        when(customerService.patchCustomer(anyLong(), any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(this.customerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + ID)));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(ID);
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(customerService.findCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}