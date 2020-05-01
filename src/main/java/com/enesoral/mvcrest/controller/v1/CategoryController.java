package com.enesoral.mvcrest.controller.v1;

import com.enesoral.mvcrest.api.v1.model.CategoryDto;
import com.enesoral.mvcrest.api.v1.model.CategoryListDto;
import com.enesoral.mvcrest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<CategoryListDto> getAllCategories() {
        return new ResponseEntity<>(new CategoryListDto(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }
}
