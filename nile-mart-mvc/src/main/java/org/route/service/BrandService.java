package org.route.service;

import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.route.repository.brand.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    public Brand findBySlug(String slug){
        return brandRepository.findBySlug(slug).orElseThrow(()->new RuntimeException("Brand not found!"));
    }

    public List<Brand> findActiveBrands(){
        return brandRepository.findAll().stream().filter(Brand::isActive).toList();
    }

    public void validateName(Brand brand){
        if (brandRepository.findAll().stream()
                .anyMatch(brand1 -> brand1.getName().equalsIgnoreCase(brand.getName()))) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Brand with Name " + brand.getName() + " already exists");
        }
    }

    public void validateSlug(Brand brand){
        if(brandRepository.findBySlug(brand.getSlug()).isPresent())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Brand with Slug "+brand.getSlug()+" already exists");
    }

    public void save(Brand brand){
        brandRepository.save(brand);
    }


}
