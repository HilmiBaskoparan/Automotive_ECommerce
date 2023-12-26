package com.hilmibaskoparan.business.requests.categoryRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    @Size(min = 4,message = "{0001}")
    private String name;

    @NotNull
    private int brandId;
}