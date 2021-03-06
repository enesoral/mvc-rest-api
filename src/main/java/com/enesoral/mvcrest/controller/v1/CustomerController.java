package com.enesoral.mvcrest.controller.v1;

import com.enesoral.mvcrest.generated.CustomerDto;
import com.enesoral.mvcrest.generated.CustomerListDto;
import com.enesoral.mvcrest.config.SwaggerConfig;
import com.enesoral.mvcrest.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.CUSTOMER_CONTROLLER_TAG})
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "This will get a list of customers.", notes = "Some additional notes about the endpoint.")
    public ResponseEntity<CustomerListDto> getCustomersList() {
        CustomerListDto customerListDto = new CustomerListDto();
        customerListDto.getCustomers().addAll(customerService.findAllCustomers());
        return new ResponseEntity<>(customerListDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}