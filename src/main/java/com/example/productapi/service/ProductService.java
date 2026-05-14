package com.example.productapi.service;

import com.example.productapi.dto.ProductCreateDTO;
import com.example.productapi.dto.ProductResponseDTO;
import com.example.productapi.dto.ProductUpdateDTO;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Random random = new Random();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ==================== GET ALL ====================
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ==================== GET BY ID ====================
    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return toResponseDTO(product);
    }

    // ==================== CREATE ====================
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO createDTO) {
        Product product = new Product();

        product.setId(generateProductId());
        product.setName(createDTO.getName());
        product.setPrice(createDTO.getPrice());
        product.setCostPrice(createDTO.getCostPrice());
        product.setImageUrl(createDTO.getImageUrl());
        product.setBarcode(createDTO.getBarcode());
        product.setCategory(createDTO.getCategory());
        product.setSupplyOrderId(createDTO.getSupplyOrderId());
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    // ==================== UPDATE ====================
    @Transactional
    public ProductResponseDTO updateProduct(String id, ProductUpdateDTO updateDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (updateDTO.getName() != null) existing.setName(updateDTO.getName());
        if (updateDTO.getPrice() != null) existing.setPrice(updateDTO.getPrice());
        if (updateDTO.getCostPrice() != null) existing.setCostPrice(updateDTO.getCostPrice());
        if (updateDTO.getImageUrl() != null) existing.setImageUrl(updateDTO.getImageUrl());
        if (updateDTO.getBarcode() != null) existing.setBarcode(updateDTO.getBarcode());
        if (updateDTO.getCategory() != null) existing.setCategory(updateDTO.getCategory());
        if (updateDTO.getSupplyOrderId() != null) existing.setSupplyOrderId(updateDTO.getSupplyOrderId());

        Product saved = productRepository.save(existing);
        return toResponseDTO(saved);
    }

    // ==================== DELETE ====================
    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    // ==================== ID GENERATOR ====================
    private String generateProductId() {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        int randomPart = 100000 + random.nextInt(900000); // 6 digit random number
        return "p" + datePart + randomPart;
    }

    // ==================== MAPPER ====================
    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCostPrice(),
                product.getImageUrl(),
                product.getBarcode(),
                product.getCategory(),
                product.getSupplyOrderId(),
                product.getCreatedAt()
        );
    }
    // ==================== BULK CREATE ====================
    @Transactional
    public List<ProductResponseDTO> createMultipleProducts(List<ProductCreateDTO> createDTOs) {
        List<Product> products = createDTOs.stream().map(dto -> {
            Product product = new Product();

            product.setId(generateProductId());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setCostPrice(dto.getCostPrice() != null ? dto.getCostPrice() : dto.getPrice()); // fallback
            product.setImageUrl(dto.getImageUrl());
            product.setBarcode(dto.getBarcode());
            product.setCategory(dto.getCategory());
            product.setSupplyOrderId(dto.getSupplyOrderId());
            product.setCreatedAt(LocalDateTime.now());

            return product;
        }).collect(Collectors.toList());

        List<Product> savedProducts = productRepository.saveAll(products);

        return savedProducts.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}