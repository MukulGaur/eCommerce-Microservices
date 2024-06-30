package com.ecommerce_microservices.product_service.service.impl;

import com.ecommerce_microservices.product_service.exception.ProductNotFoundException;
import com.ecommerce_microservices.product_service.model.Product;
import com.ecommerce_microservices.product_service.repository.ProductRepository;
import com.ecommerce_microservices.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            return Optional.of(productRepository.save(existingProduct));
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> optionalProduct =productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return "Product with id '%s' is deleted.".formatted(id);
        } else{
            throw new ProductNotFoundException(id);
        }
    }
}
