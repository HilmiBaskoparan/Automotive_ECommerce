package com.hilmibaskoparan.business.requests.brandRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBrandRequest {

    @NotBlank
    private String name;
}
