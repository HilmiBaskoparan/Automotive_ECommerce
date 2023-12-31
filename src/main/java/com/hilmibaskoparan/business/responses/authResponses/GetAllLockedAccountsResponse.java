package com.hilmibaskoparan.business.responses.authResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllLockedAccountsResponse {

    private int id;
    private String username;
    private String email;
    private int failedLoginAttempts;

}
