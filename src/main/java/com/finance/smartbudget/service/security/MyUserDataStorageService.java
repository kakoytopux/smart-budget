package com.finance.smartbudget.service.security;

import com.finance.smartbudget.model.user.User;
import com.finance.smartbudget.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MyUserDataStorageService {
    private final UserRepository userRepository;
    public User getMyUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String myUsername = authentication.getName();
        Optional<User> myUserOptional = userRepository.findByUsername(myUsername);
        if(myUserOptional.isPresent()) {
            return myUserOptional.get();
        }
        throw new IllegalArgumentException("Your account does not exists");
    }
    public String getMyUsername () {
        return getMyUser().getUsername();
    }
}