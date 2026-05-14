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

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductCreateDTO createDTO) {
        ProductResponseDTO created = productService.createProduct(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(
            @PathVariable String id,
            @RequestBody ProductUpdateDTO updateDTO) {
        return productService.updateProduct(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    // BULK CREATE
    @PostMapping("/bulk")
    public ResponseEntity<List<ProductResponseDTO>> createBulk(@RequestBody List<ProductCreateDTO> createDTOs) {
        List<ProductResponseDTO> created = productService.createMultipleProducts(createDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}