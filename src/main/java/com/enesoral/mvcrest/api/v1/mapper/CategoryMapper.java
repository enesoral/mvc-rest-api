package com.enesoral.mvcrest.api.v1.mapper;

import com.enesoral.mvcrest.api.v1.model.CategoryDto;
import com.enesoral.mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);
}
