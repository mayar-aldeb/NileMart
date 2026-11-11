package org.route.repository.brand;

import org.route.model.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository {
    List<Brand> findAll();

    Optional<Brand> findBySlug(String slug);

    void save(Brand brand);
}
