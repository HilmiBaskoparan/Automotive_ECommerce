package com.hilmibaskoparan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Boolean sex;
    private LocalDate birthDate;
    private BasketDTO basketDTO;
    private List<CreditCardDTO> creditCardDTOS;
    private List<OrderDTO> orderDTOS;
    private List<UserLogDTO> userLogDTOS;
}
