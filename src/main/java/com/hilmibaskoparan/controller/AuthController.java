package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.AuthService;
import com.hilmibaskoparan.business.abstracts.EmailService;
import com.hilmibaskoparan.business.requests.authRequests.LoginRequest;
import com.hilmibaskoparan.business.requests.authRequests.RefreshTokenRequest;
import com.hilmibaskoparan.business.requests.authRequests.RegisterRequest;
import com.hilmibaskoparan.business.responses.authResponses.GetAllLockedAccountsResponse;
import com.hilmibaskoparan.business.responses.authResponses.LoginResponse;
import com.hilmibaskoparan.business.responses.authResponses.RefreshTokenResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @Value("app.email_verified_callbackurl")
    private String emailVerifiedCallbackUrl;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        emailService.confirmEmail(confirmationToken);

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(emailVerifiedCallbackUrl + "/auth/login")).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/accountLockedOpen")
    public void accountLockedOpen(@RequestParam(name = "userId") int userId) {
        authService.accountLockedOpen(userId);
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return this.authService.refreshToken(refreshTokenRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllLockedAccounts")
    public List<GetAllLockedAccountsResponse> getAllLockedAccounts() {
        return this.authService.getAllLockedAccounts();
    }

}
