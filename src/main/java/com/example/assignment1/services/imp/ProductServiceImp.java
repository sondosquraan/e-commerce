package com.example.assignment1.services.imp;

import com.example.assignment1.services.ProductServices;
import org.springframework.stereotype.Service;
import com.example.assignment1.entity.Product;
import com.example.assignment1.exceptions.ResourceNotFoundException;
import com.example.assignment1.dto.ProductDto;
import com.example.assignment1.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

    @Service //To enable this class for component scanning
    public class ProductServiceImp implements ProductServices {

        private ProductRepository productRepository;

        public ProductServiceImp(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }



        @Override
        public ProductDto createProduct(ProductDto productDto) {

            // convert DTO to entity
            Product product = mapToEntity(productDto);
            Product newProduct = productRepository.save(product);

            // convert entity to DTO
            ProductDto productResponse = mapToDTO(newProduct);
            return productResponse;
        }



        @Override
        public List<ProductDto> getAllProducts() {
            List<Product> products =productRepository.findAll();
            return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());        }

        @Override
        public ProductDto getProductById(long id) {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            return mapToDTO(product);
        }

        @Override
        public ProductDto updateProduct(ProductDto productDto, long id) {
            // get product by id from the database
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            //product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setType(productDto.getType());
            product.setDateOfExpiry(productDto.getDateOfExpiry());


            Product updatedProduct= productRepository.save(product);
            return mapToDTO(updatedProduct);
        }

        @Override
        public void deleteProductById(long id) {
            // get Product by id from the database
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            productRepository.delete(product);
        }

        // convert Entity into DTO
        private ProductDto mapToDTO(Product product) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setType(product.getType());
            productDto.setDateOfExpiry(product.getDateOfExpiry());
            return productDto;
        }

        // convert DTO to entity
        private Product mapToEntity(ProductDto productDto) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setType(productDto.getType());
            product.setDateOfExpiry(productDto.getDateOfExpiry());

            return product;
        }
    }

