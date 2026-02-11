package com.example.productapi.controller;

import com.example.productapi.dto.ProductCreateDTO;
import com.example.productapi.dto.ProductResponseDTO;
import com.example.productapi.dto.ProductUpdateDTO;
import com.example.productapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL
    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.getAllProducts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductCreateDTO createDTO) {
        ProductResponseDTO created = productService.createProduct(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ProductResponseDTO update(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO updateDTO) {
        return productService.updateProduct(id, updateDTO);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}