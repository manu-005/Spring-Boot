package com.learning.eCommerce.service.productService;

import com.learning.eCommerce.dto.productsDTO.ProductRequestDto;
import com.learning.eCommerce.dto.productsDTO.ProductResponseDto;
import com.learning.eCommerce.entity.category.Category;
import com.learning.eCommerce.entity.products.ProductEntity;
import com.learning.eCommerce.entity.products.ProductImage;
import com.learning.eCommerce.enums.ProductStatusEnum;
import com.learning.eCommerce.exception.productException.CategoryNotFoundException;
import com.learning.eCommerce.exception.productException.ProductNotFoundException;
import com.learning.eCommerce.exception.productException.SkuAlreadyExistsException;
import com.learning.eCommerce.mapper.ProductMapper;
import com.learning.eCommerce.repository.CategoryRepository;
import com.learning.eCommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        if (productRepository.existsBySku(dto.getSku())) {
            throw new SkuAlreadyExistsException("SKU already exists");
        }

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found"));

        ProductEntity entity = productMapper.toEntity(dto);


        entity.setCategory(category);

        if (dto.getQuantity() == 0) {
            entity.setStatus(ProductStatusEnum.OUT_OF_STOCK);
        } else {
            entity.setStatus(ProductStatusEnum.ACTIVE);
        }

        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {

            List<ProductImage> images = new ArrayList<>();

            for (String imageUrl : dto.getImageUrls()) {

                ProductImage image = new ProductImage();

                image.setImageUrl(imageUrl);
                image.setProduct(entity);

                images.add(image);
            }

            entity.setImages(images);
        }
        ProductEntity savedProduct = productRepository.save(entity);
        return productMapper.toResponseDto(entity);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return productMapper.toResponseDto(entity);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        List<ProductEntity> entities = productRepository.findAll();

        return entities.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {

        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));

        if (!existingProduct.getSku().equals(requestDto.getSku())
                && productRepository.existsBySku(requestDto.getSku())) {

            throw new SkuAlreadyExistsException(
                    "SKU already exists: " + requestDto.getSku());
        }

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: "
                                        + requestDto.getCategoryId()));

        productMapper.updateProductFromDto(requestDto, existingProduct);

        ProductEntity updatedProduct = productRepository.save(existingProduct);

        return productMapper.toResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));
        productRepository.delete(product);
    }
}