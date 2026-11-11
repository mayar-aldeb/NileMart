package org.route.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.route.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories =  categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("topLevelCategories", categoryService.findAllTopLevelCategories());
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @PostMapping
    public String AddCategory(Model model, @ModelAttribute("category") @Valid Category category, BindingResult result) {
        try {
            categoryService.validateCategoryOrder(category);
        }catch (HttpClientErrorException e){
            result.rejectValue("order",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }
        try{
            categoryService.validateCategoryParentSlug(category);
        }catch (HttpClientErrorException e){
            result.rejectValue("parentSlug",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }
        try{
            categoryService.validateCategorySlug(category);
        }catch(HttpClientErrorException e){
            result.rejectValue("slug",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }
        try{
            categoryService.validateCategoryName(category);
        }catch(HttpClientErrorException e){
            result.rejectValue("name",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }


        if (result.hasErrors()) {
            model.addAttribute("topLevelCategories", categoryService.findAllTopLevelCategories());
            return "categoryForm";
        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

}
