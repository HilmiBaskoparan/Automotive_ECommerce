package com.hilmibaskoparan.business.requests.authRequests;

import com.hilmibaskoparan.validator.UniqueEmail;
import com.hilmibaskoparan.validator.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @UniqueUserName
    private String userName;

    @UniqueEmail
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
