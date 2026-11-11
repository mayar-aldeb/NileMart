package org.route.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.route.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public String getBrands(Model model) {
        List<Brand> brands =  brandService.findAll();
        model.addAttribute("brands", brands);
        return "brands";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "brandForm";
    }

    @PostMapping
    public String addBrand(@ModelAttribute("brand") @Valid Brand brand, BindingResult result) {
        try {
            brandService.validateSlug(brand);
        } catch (HttpClientErrorException e) {
            result.rejectValue("slug",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }
        try {
            brandService.validateName(brand);
        }catch (HttpClientErrorException e){
            result.rejectValue("name",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }

        if (result.hasErrors()) {
            return "brandForm";
        }
        brandService.save(brand);
        return "redirect:/brands";
    }
}
