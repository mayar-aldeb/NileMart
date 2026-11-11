package org.route.service;

import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.route.repository.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        var subCategories = categoryRepository.findAll().stream()
                .filter(category -> !category.getParentSlug().isEmpty())
                .collect(Collectors.groupingBy(Category::getParentSlug));

        return categoryRepository.findAll().stream()
                .filter(category -> category.getParentSlug().isEmpty())
                .sorted(Comparator.comparing(Category::getOrder))
                .flatMap(parent-> Stream.concat(
                        Stream.of(parent),
                        subCategories.getOrDefault(parent.getSlug(),List.of())
                                .stream()
                                .sorted(Comparator.comparing(Category::getOrder))))
                .toList();
    }

    public Category findCategoryBySlug(String slug){
        return categoryRepository.findBySlug(slug).orElseThrow(()->new RuntimeException("Category not found"));
    }

    public List<Category> findAllTopLevelCategories() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParentSlug().isEmpty())
                .filter(Category::isActive)
                .toList();
    }

    public List<Category> findAllActiveSubCategories() {
        return categoryRepository.findAll().stream()
                .filter(Category::isActive)
                .filter(category -> !category.getParentSlug().isEmpty()).toList();
    }

    public void validateCategorySlug(Category category) {
        if(categoryRepository.findAll().stream().anyMatch(c->category.getSlug().equals(c.getSlug()))){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"slug already exists");
        }
    }

    public void validateCategoryName(Category category) {
        if(categoryRepository.findAll().stream().
                anyMatch(c->category.getName().equalsIgnoreCase(c.getName())
                && category.getParentSlug().equals(c.getParentSlug()))){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"name already exists");
        }
    }

    public void validateCategoryParentSlug(Category category) {
        if(category.getParentSlug().isEmpty())
            return;
        if(categoryRepository.findAll().stream().
                noneMatch(c->category.getParentSlug().equals(c.getSlug())
                        &&c.getParentSlug().isEmpty()
                        &&c.isActive())){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"invalid parent slug");
        }

    }

    public void validateCategoryOrder(Category category) {
        for(Category category1 : categoryRepository.findAll()){
            if(category1.getOrder()==category.getOrder()
                    && category1.getParentSlug().equals(category.getParentSlug()))
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"order already exists") ;
        }
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
