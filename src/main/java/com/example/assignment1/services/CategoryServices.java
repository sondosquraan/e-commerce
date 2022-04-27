package com.example.assignment1.services;

import com.example.assignment1.dto.CategoryDto;
import java.util.List;

public interface CategoryServices {


    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto categoryDto, long id);

    void deleteCategoryById(long id);



}
