package com.finance.smartbudget.security.security_module.service.auth;

import com.finance.smartbudget.security.security_module.exceptions.IncorrectSignUpDataException;
import com.finance.smartbudget.security.security_module.models.User;
import com.finance.smartbudget.security.security_module.repository.UserRepository;
import com.finance.smartbudget.security.security_module.web.request.SignUpRequest;
import com.finance.smartbudget.security.security_module.web.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws IncorrectSignUpDataException {
        throwExceptionIfRequestDataTaken(signUpRequest);
        initAndSaveNewUser(signUpRequest);
        return (new SignUpResponse("User registered successfully!"));
    }

    private void initAndSaveNewUser(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),BigDecimal.ZERO);

        userRepository.save(user);
    }

    private void throwExceptionIfRequestDataTaken(SignUpRequest signUpRequest) throws IncorrectSignUpDataException {
        if (userRepository.existsByUsername(signUpRequest.getUsername()) || userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new IncorrectSignUpDataException(new SignUpResponse("Username or email there is now!"));
        }
    }
}
