package org.route.controller;


import jakarta.validation.Valid;
import org.route.model.Product;
import org.route.service.BrandService;
import org.route.service.CategoryService;
import org.route.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("brands",brandService.findAll());
        model.addAttribute("availableProducts", productService.getAvailableProducts());
        return "products";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories",categoryService.findAllActiveSubCategories());
        model.addAttribute("brands",brandService.findActiveBrands());
        return "productForm";
    }

    @PostMapping
    public String addProduct(Model model,@ModelAttribute("product") @Valid Product product,
                             BindingResult result) {
        try {
            productService.validateProductBrand(product);
        }catch (HttpClientErrorException e){
            result.rejectValue("brandSlug",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }
        try {
            productService.validateProductCategory(product);
        }catch (HttpClientErrorException e){
            result.rejectValue("categorySlug",String.valueOf(e.getStatusCode().value()),e.getMessage());
        }


        if (productService.isProductExist(product.getSku())) {
            result.rejectValue("sku", "400", "sku is already exist");
        }
        if (result.hasErrors()) {
            model.addAttribute("categories",categoryService.findAllActiveSubCategories());
            model.addAttribute("brands",brandService.findActiveBrands());
            return "productForm";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
