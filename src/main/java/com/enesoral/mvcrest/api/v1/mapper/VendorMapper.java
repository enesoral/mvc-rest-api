package com.enesoral.mvcrest.api.v1.mapper;

import com.enesoral.mvcrest.api.v1.model.VendorDto;
import com.enesoral.mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto vendorToVendorDto(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDto vendorDto);
}
