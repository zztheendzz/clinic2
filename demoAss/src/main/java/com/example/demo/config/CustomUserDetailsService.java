package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

    @Override
// xác định người dùng khi đăng nhập qua gmail
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	System.out.println(email);
        Optional<User> user = repository.findByEmail(email);
        System.out.println("user la"+user.get().getEmail());
        return user.map(u -> new CustomUserDetails(u))
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
    }
}