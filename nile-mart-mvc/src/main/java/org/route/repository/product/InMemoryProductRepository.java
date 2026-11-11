package org.route.repository.product;

import jakarta.annotation.PostConstruct;
import org.route.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Repository
public class InMemoryProductRepository implements ProductRepository {
    List<Product> myProducts = new ArrayList<>();

    @PostConstruct
    void init() {
        myProducts.add(Product.builder()
                .sku("P002")
                .name("Desk Lamp")
                .price(BigDecimal.valueOf(500))
                .inStock(10)
                .categorySlug("lighting")
                .build());
        myProducts.add(Product.builder()
                .sku("P003")
                .name("ASUS TUF")
                .price(BigDecimal.valueOf(24000))
                .inStock(4)
                .categorySlug("laptops")
                .brandSlug("asus")
                .build());
        myProducts.add(Product.builder()
                .sku("PH-A55-128")
                .name("Samsung Galaxy A55 128GB")
                .price(BigDecimal.valueOf(18500))
                .inStock(7)
                .categorySlug("mobile-phones")
                .brandSlug("samsung")
                .build());
    }

    @Override
    public List<Product> findAll() {
        return myProducts;
    }

    @Override
    public Product findBySku(String sku) {
        for (Product product : myProducts) {
            if (product.getSku().equals(sku)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        myProducts.add(product);
    }
}
