package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.authRequests.LoginRequest;
import com.hilmibaskoparan.business.requests.authRequests.RefreshTokenRequest;
import com.hilmibaskoparan.business.requests.authRequests.RegisterRequest;
import com.hilmibaskoparan.business.responses.authResponses.GetAllLockedAccountsResponse;
import com.hilmibaskoparan.business.responses.authResponses.LoginResponse;
import com.hilmibaskoparan.business.responses.authResponses.RefreshTokenResponse;

import java.util.List;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    String registerUser(RegisterRequest registerRequest);

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    public void accountLockedOpen(int userId);

    List<GetAllLockedAccountsResponse> getAllLockedAccounts();

}
