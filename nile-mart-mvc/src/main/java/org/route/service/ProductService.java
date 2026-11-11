package org.route.service;


import org.route.model.Brand;
import org.route.model.Category;
import org.route.model.Product;
import org.route.repository.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private  final BrandService brandService;


    public ProductService(ProductRepository productRepository, CategoryService categoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            if (p.getInStock() > 0)
                availableProducts.add(p);
        }
        return availableProducts;
    }

    public void validateProductCategory(Product product){
        Category category;
        try {
            category=categoryService.findCategoryBySlug(product.getCategorySlug());
        }catch (RuntimeException e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        if(category.getParentSlug().isEmpty() || !category.isActive())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"invalid Category");
    }

    public void validateProductBrand(Product product){
        if(product.getBrandSlug().isEmpty())
            return;
        Brand brand;
        try {
            brand=brandService.findBySlug(product.getBrandSlug());
        }catch (RuntimeException e){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        if(!brand.isActive())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Brand not active");


    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public boolean isProductExist(String sku) {
        return productRepository.findBySku(sku) != null;
    }
}
