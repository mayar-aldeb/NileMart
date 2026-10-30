package org.route.service;

import lombok.RequiredArgsConstructor;
import org.route.exception.StoreException;
import org.route.model.Product;
import org.route.repository.ProductRepository;
import org.route.service.pricing.PricingService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;
    private final PricingService pricingService;

    public void findAll() {
        List<Product> products = repository.findAll();
        if (products.isEmpty())
            throw new StoreException("No products found");
        products.forEach(System.out::println);
    }

    public Product findBySku(String sku) {
        return repository.findBySku(sku)
                .filter(Product::isActive)
                .orElseThrow(() -> new StoreException("Product not found."));
    }

    public BigDecimal getPrice(String sku, int qty) {
        Product product = findBySku(sku);
        return pricingService.getPrice(product, qty);
    }

    public void reduceStock(String sku, int qty) {
        checkQuantity(sku,qty);
        Product product = findBySku(sku);
        product.setStockQuantity(product.getStockQuantity() - qty);
    }

    public void checkQuantity(String sku, int qty) {
        Product product = findBySku(sku);
        if(product.getStockQuantity() ==0)
            throw new StoreException("product out of stock.");
        if(product.getStockQuantity()<qty)
            throw new StoreException("product "+product.getName()+" quantity is not enough.");
    }
}
