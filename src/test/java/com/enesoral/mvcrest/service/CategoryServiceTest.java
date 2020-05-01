package com.enesoral.mvcrest.service;

import com.enesoral.mvcrest.api.v1.mapper.CategoryMapper;
import com.enesoral.mvcrest.api.v1.model.CategoryDto;
import com.enesoral.mvcrest.domain.Category;
import com.enesoral.mvcrest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Fresh";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        assertEquals(categories.size(), categoryDtos.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(NAME)).thenReturn(category);

        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }
}