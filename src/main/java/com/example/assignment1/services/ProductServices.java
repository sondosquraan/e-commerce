package com.example.assignment1.services;
import java.util.List;

import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.dto.ProductDto;


public interface ProductServices {
    ProductDto createProduct (ProductDto productDto);

    List<ProductDto> getAllProducts();


    ProductDto getProductById(long id);

    ProductDto updateProduct(ProductDto productDto, long id);

    void deleteProductById(long id);

}
