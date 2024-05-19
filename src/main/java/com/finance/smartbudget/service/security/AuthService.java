package com.finance.smartbudget.service.security;

import com.finance.smartbudget.dto.UserDto;
import com.finance.smartbudget.service.UserService;
import com.finance.smartbudget.web.JwtTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtTokenResponse signUp(UserDto request) {
        UserDetails springUserDetails =
            User
            .builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

        userService.encryptPasswordAndSaveNewUser(new UserDto(
            springUserDetails.getUsername(),
            springUserDetails.getPassword()));

        var jwt = jwtService.generateToken(springUserDetails);
        return new JwtTokenResponse(jwt);
    }

    public JwtTokenResponse signIn(UserDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

       UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(userDetails);
        return new JwtTokenResponse(jwt);
    }

}