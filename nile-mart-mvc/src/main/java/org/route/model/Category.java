package org.route.model;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$",message = "slug must be lower case and '-' sign only")
    private String slug;
    private String parentSlug;
    @Positive(message = "order is required")
    private int order;
    private boolean active;


}
