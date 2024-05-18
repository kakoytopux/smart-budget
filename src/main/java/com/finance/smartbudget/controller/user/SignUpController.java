package com.finance.smartbudget.controller.user;

import com.finance.smartbudget.dto.UserDto;
import com.finance.smartbudget.model.user.User;
import com.finance.smartbudget.repository.UserRepository;
import com.finance.smartbudget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signUpNewUser(@RequestBody UserDto userDto) {
        String status = userService.encryptPasswordAndSaveNewUser(userDto);
        boolean isSignUpSuccess = status.isEmpty();
        if(isSignUpSuccess) {
            return ResponseEntity.ok(Map.of("is_signup_success","true","error_message","no errors"));
        }
        return ResponseEntity.badRequest().body(Map.of("is_signup_success","false","error_message","there is user with such username"));

    }
}
