package org.route.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$",message = "slug must be lower case and '-' sign only")
    private String slug;
    private boolean active;

}
