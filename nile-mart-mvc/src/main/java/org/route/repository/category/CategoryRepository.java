package org.route.repository.category;

import org.route.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository {
    List<Category> findAll();

    Optional<Category> findBySlug(String slug);

    void save(Category category);
}
