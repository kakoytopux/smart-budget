package com.finance.smartbudget.controller.user;


import com.finance.smartbudget.dto.UserDto;
import com.finance.smartbudget.service.security.AuthService;
import com.finance.smartbudget.web.JwtTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Вход пользователя в систему
     */
    @PostMapping("/sign-in")
    public JwtTokenResponse signIn(@RequestBody UserDto authDto) {
        return authService.signIn(authDto);
    }

    /**
     * Регистрации нового пользователя с уникальным именем
     */
    @PostMapping("/sign-up")
    public JwtTokenResponse signUpNewUser(@RequestBody UserDto authDto) {
        return authService.signUp(authDto);
    }
}
