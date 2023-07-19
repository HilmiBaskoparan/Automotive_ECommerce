package com.hilmibaskoparan.dto;

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
public class UserLogDTO {

    private String logDescription;
    private Date loginDate;
    private Date logoutDate;
    private UserDTO userDTO;
}
