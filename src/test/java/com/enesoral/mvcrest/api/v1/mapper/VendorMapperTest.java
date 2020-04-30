package com.enesoral.mvcrest.api.v1.mapper;

import com.enesoral.mvcrest.api.v1.model.VendorDto;
import com.enesoral.mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "Walmart";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDtoTest() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);

        assertEquals(NAME, vendorDto.getName());
    }

    @Test
    public void customerDtoToCustomerTest() {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);

        assertEquals(NAME, vendor.getName());
    }
}
