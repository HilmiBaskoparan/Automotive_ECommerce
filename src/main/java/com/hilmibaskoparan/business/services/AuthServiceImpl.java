package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.AuthService;
import com.hilmibaskoparan.business.abstracts.EmailService;
import com.hilmibaskoparan.business.abstracts.RefreshTokenService;
import com.hilmibaskoparan.business.requests.authRequests.LoginRequest;
import com.hilmibaskoparan.business.requests.authRequests.RefreshTokenRequest;
import com.hilmibaskoparan.business.requests.authRequests.RegisterRequest;
import com.hilmibaskoparan.business.responses.authResponses.GetAllLockedAccountsResponse;
import com.hilmibaskoparan.business.responses.authResponses.LoginResponse;
import com.hilmibaskoparan.business.responses.authResponses.RefreshTokenResponse;
import com.hilmibaskoparan.config.AppProperties;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.exception.TokenRefreshException;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.CustomerRepository;
import com.hilmibaskoparan.repository.RoleRepository;
import com.hilmibaskoparan.repository.UserRepository;
import com.hilmibaskoparan.security.UserDetailsImpl;
import com.hilmibaskoparan.util.JwtUtils;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("authorization")
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final AppProperties appProperties;
    private final ModelMapperService modelMapperService;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, CustomerRepository customerRepository, EmailService emailService,
                           AppProperties appProperties, ModelMapperService modelMapperService, PasswordEncoder passwordEncoder,
                           JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
        this.appProperties = appProperties;
        this.modelMapperService = modelMapperService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        Customer customer = customerRepository.findByEmail(loginRequest.getEmail());

        return new LoginResponse(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getUsername(), customer.getEmail(), jwt, refreshToken.getToken(),
                System.currentTimeMillis() + appProperties.getAuth().getJwtExpirationMs());
    }

    @Transactional
    @Override
    public String registerUser(RegisterRequest registerRequest) {

        Customer customer = new Customer(registerRequest.getFirstName(), registerRequest.getLastName(),
                registerRequest.getUserName(), passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getEmail());

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());

        customer.setRoles(roles);
        customerRepository.save(customer);
        try {
            emailService.sendEmail(customer);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "Verify email by the link sent on your email address";
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new TokenRefreshException(refreshTokenRequest.getRefreshToken(),
                        "Refresh token is not in database!"));
        User user = refreshToken.getUser();
        String token = jwtUtils.generateTokenFromEmail(user.getEmail());

        return new RefreshTokenResponse(token, refreshTokenRequest.getRefreshToken(),
                System.currentTimeMillis() + appProperties.getAuth().getJwtExpirationMs());
    }

    @Transactional
    @Override
    public void accountLockedOpen(int userId) {

        User user = userRepository.findById(userId).get();

        user.setAccountNonLocked(true);
        user.setFailedLoginAttempts(0);

        userRepository.save(user);

    }

    @Override
    public List<GetAllLockedAccountsResponse> getAllLockedAccounts() {

        List<User> users = userRepository.findByAccountNonLockedIsFalse();

        List<GetAllLockedAccountsResponse> response = users.stream()
                .map((user) -> modelMapperService.forResponse().map(user, GetAllLockedAccountsResponse.class))
                .collect(Collectors.toList());

        return response;
    }
}
