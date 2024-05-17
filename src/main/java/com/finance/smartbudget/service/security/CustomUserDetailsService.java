package com.finance.smartbudget.service.security;


import com.finance.smartbudget.config.security.CustomUserDetails;
import com.finance.smartbudget.model.user.User;
import com.finance.smartbudget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username +  " :username not found!"));
    }
}
