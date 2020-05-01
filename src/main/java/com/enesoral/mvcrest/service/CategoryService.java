package com.enesoral.mvcrest.service;

import com.enesoral.mvcrest.api.v1.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryByName(String name);
}
