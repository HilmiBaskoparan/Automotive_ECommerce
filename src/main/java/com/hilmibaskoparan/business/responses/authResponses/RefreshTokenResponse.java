package com.hilmibaskoparan.business.responses.authResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiry;

}
