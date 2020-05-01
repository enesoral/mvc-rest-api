package com.enesoral.mvcrest.bootstrap;

import com.enesoral.mvcrest.domain.Category;
import com.enesoral.mvcrest.domain.Customer;
import com.enesoral.mvcrest.domain.Vendor;
import com.enesoral.mvcrest.repository.CategoryRepository;
import com.enesoral.mvcrest.repository.CustomerRepository;
import com.enesoral.mvcrest.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");
        customerRepository.save(customer2);
    }
}
