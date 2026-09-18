package com.shopsphere.shopsphere_backend.security;

import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import springframework.security.core.userdetails.UserDetailsService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User  user = userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username: " + username));
        return new CustomUserPrincipal(user);
    }
}
