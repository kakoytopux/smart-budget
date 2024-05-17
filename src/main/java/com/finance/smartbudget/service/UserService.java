package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.UserDto;
import com.finance.smartbudget.model.user.User;
import com.finance.smartbudget.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Transactional
    public void encryptPasswordAndSaveNewUser(UserDto userDto) {
        userRepository.save(new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),new BigDecimal(0)));

    }
}
