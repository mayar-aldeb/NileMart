package org.route.repository;

import org.route.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    void save(Product product);
}
