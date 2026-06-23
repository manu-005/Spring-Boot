package com.learning.eCommerce.service.productService;

import com.learning.eCommerce.dto.productsDTO.ProductRequestDto;
import com.learning.eCommerce.dto.productsDTO.ProductResponseDto;
import com.learning.eCommerce.entity.category.Category;
import com.learning.eCommerce.entity.products.ProductEntity;
import com.learning.eCommerce.entity.products.ProductImage;
import com.learning.eCommerce.enums.ProductStatusEnum;
import com.learning.eCommerce.mapper.ProductMapper;
import com.learning.eCommerce.repository.CategoryRepository;
import com.learning.eCommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto dto) {

        if (productRepository.existsBySku(dto.getSku())) {
            throw new RuntimeException("SKU already exists");
        }

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        ProductEntity product = new ProductEntity();

        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setBrand(dto.getBrand());

        product.setCategory(category);

        if (dto.getQuantity() == 0) {
            product.setStatus(ProductStatusEnum.OUT_OF_STOCK);
        } else {
            product.setStatus(ProductStatusEnum.ACTIVE);
        }

        if (dto.getImageUrls() != null) {

            List<ProductImage> images = new ArrayList<>();

            for (String imageUrl : dto.getImageUrls()) {

                ProductImage image = new ProductImage();

                image.setImageUrl(imageUrl);
                image.setProduct(product);

                images.add(image);
            }

            product.setImages(images);
        }

        ProductEntity savedProduct = productRepository.save(product);

        ProductResponseDto response = productMapper.toResponseDto(savedProduct);
        return response;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}