package com.ecommerce_microservices.product_service.service;

import com.ecommerce_microservices.product_service.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getProductByCategory(String category);
    List<Product> getAllProducts();
    Optional<Product> updateProduct(Long id, Product product);
    String deleteProduct(Long id);
}
