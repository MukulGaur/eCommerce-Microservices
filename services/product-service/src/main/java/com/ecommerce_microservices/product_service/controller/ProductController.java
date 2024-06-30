package com.ecommerce_microservices.product_service.controller;

import com.ecommerce_microservices.product_service.model.Product;
import com.ecommerce_microservices.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create new product", description = "Create new product")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve all products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = Optional.ofNullable(productService.getProductById(id));
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get product by category", description = "Retrieve a product by category")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category){
        Optional<List<Product>> listOfProductByCategory = Optional.ofNullable(productService.getProductByCategory(category));
        return listOfProductByCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by ID", description = "Update a product by ID")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> updatedProduct = productService.updateProduct(id, product);
        return updatedProduct.map(u -> ResponseEntity.ok("Product updated successfully"))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID", description = "Delete a product by ID")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
