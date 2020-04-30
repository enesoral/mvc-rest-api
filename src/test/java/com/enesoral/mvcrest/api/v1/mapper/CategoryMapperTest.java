package com.enesoral.mvcrest.api.v1.mapper;

import com.enesoral.mvcrest.api.v1.model.CategoryDto;
import com.enesoral.mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Fresh";
    public static final Long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDtoTest() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);

        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }
}
