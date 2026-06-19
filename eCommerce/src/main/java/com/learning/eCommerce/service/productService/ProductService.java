package com.learning.eCommerce.service.productService;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductDto dto) {
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        ProductEntity entity = productMapper.toEntity(dto, category);
        ProductEntity saved = productRepository.save(entity);
        return productMapper.toResponseDto(saved);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    public ProductResponseDto getProductById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toResponseDto(entity);
    }

    public ProductResponseDto updateProduct(Long id, ProductDto dto) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setStockQuantity(dto.getStockQuantity());
        entity.setCategory(category);

        ProductEntity updated = productRepository.save(entity);
        return productMapper.toResponseDto(updated);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

