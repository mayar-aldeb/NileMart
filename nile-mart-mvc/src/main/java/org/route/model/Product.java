package org.route.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotBlank(message = "SKU can't be empty.")
    private String sku;
    @NotBlank
    private String name;
    @NotBlank
    private String categorySlug;
    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than zero.")
    private BigDecimal price;
    @NotNull(message = "stock is required")
    @PositiveOrZero
    private Integer inStock;
    @Builder.Default
    private String brandSlug="";
}
