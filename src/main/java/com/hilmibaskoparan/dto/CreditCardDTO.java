package com.hilmibaskoparan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class CreditCardDTO {

    @NotNull
    private String cardName;

    @NotEmpty
    private String cardNumber;

    @NotNull
    private Date expirationDate;

    @NotEmpty
    private UserDTO userDTO;
}
