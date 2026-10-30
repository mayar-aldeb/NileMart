package org.route.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.route.exception.StoreException;
import org.route.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class InMemoryProductRepository implements ProductRepository {

    private final HashMap<String, Product> store = new HashMap<>();

    @PostConstruct
    void saveProducts() {
        save(createProduct(
                "PH-X5-128",
                "Smartphone X5 128GB",
                "Electronics",
                BigDecimal.valueOf(16000),
                12));

        save(createProduct(
                "ACC-CHG-20W",
                "Fast Charger 20W",
                "Electronics",
                BigDecimal.valueOf(650),
                40));

        save(createProduct(
                "ACC-BUDS-6",
                "Wireless Earbuds",
                "Electronics",
                BigDecimal.valueOf(1100),
                8));

//        save(createProduct(
//                "FSH-GAL-001",
//                "Cotton Galabeya",
//                "Fashion",
//                BigDecimal.valueOf(900),
//                25));


        save(Product.builder().
                sku("FSH-GAL-001").
                name("Cotton Galabeya").
                category("Fashion").
                price(BigDecimal.valueOf(900)).
                stockQuantity(25).
                isActive(false).
                build());

        save(createProduct(
                "HOM-PAN-28",
                "Non-stick Frying Pan 28cm",
                "Home & Kitchen",
                BigDecimal.valueOf(1200),
                3));

        save(createProduct(
                "HOM-SPC-RCK",
                "Wooden Spice Rack",
                "Home & Kitchen",
                BigDecimal.valueOf(750),
                10));

        save(createProduct(
                "BTY-SUN-50",
                "Sun Cream SPF50",
                "Beauty",
                BigDecimal.valueOf(350),
                0));

        save(createProduct(
                "BK-CAI-TRI",
                "The Cairo Trilogy",
                "Books",
                BigDecimal.valueOf(450),
                15));
    }

    @PreDestroy
    void closeCatalog() {
        System.out.println("close catalog");
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(store.get(sku));
    }

    @Override
    public List<Product> findAll() {
        return store.values().stream().toList();
    }

    public Product createProduct(String sku,String name, String category, BigDecimal price, int stockQuantity) {
        if(store.containsKey(sku))
            throw new StoreException("Product with sku " + sku + " already exists");
        return Product.builder().
                sku(sku).
                name(name).
                category(category).
                price(price).
                stockQuantity(stockQuantity).
                build();
    }

    @Override
    public void save(Product product) {
        store.put(product.getSku(), product);
    }




}
