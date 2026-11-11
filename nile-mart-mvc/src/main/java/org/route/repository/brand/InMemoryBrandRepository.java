package org.route.repository.brand;

import jakarta.annotation.PostConstruct;
import org.route.model.Brand;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBrandRepository implements BrandRepository{
    private final List<Brand> brands=new ArrayList<>();

    @PostConstruct
    public void init(){
        Brand brand=new Brand("Samsung","samsung",true);
        save(brand);
        brand=new Brand("Asus","asus",true);
        save(brand);
        brand=new Brand("Anker","anker",true);
        save(brand);
        brand=new Brand("Tefal","tefal",true);
        save(brand);
        brand=new Brand("Old Brand ","old-brand ",false);
        save(brand);
    }

    @Override
    public List<Brand> findAll() {
       return brands;
    }

    @Override
    public Optional<Brand> findBySlug(String slug) {
        return brands.stream()
                .filter(brand -> brand.getSlug().equals(slug))
                .findFirst();
    }

    @Override
    public void save(Brand brand) {
        brands.add(brand);
    }
}
