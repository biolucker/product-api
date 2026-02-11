package com.example.productapi.service;

import com.example.productapi.dto.ProductCreateDTO;
import com.example.productapi.dto.ProductResponseDTO;
import com.example.productapi.dto.ProductUpdateDTO;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET ALL - return DTOs
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID - return DTO
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return toResponseDTO(product);
    }

    // CREATE - accept DTO, return DTO
    public ProductResponseDTO createProduct(ProductCreateDTO createDTO) {
        Product product = new Product();
        product.setName(createDTO.getName());
        product.setPrice(createDTO.getPrice());
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    // UPDATE - accept DTO, return DTO
    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO updateDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        existing.setName(updateDTO.getName());
        existing.setPrice(updateDTO.getPrice());

        Product saved = productRepository.save(existing);
        return toResponseDTO(saved);
    }

    // DELETE - remains the same
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Helper method: entity → response DTO
    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }
}