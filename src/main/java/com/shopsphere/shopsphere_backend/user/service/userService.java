package com.shopsphere.shopsphere_backend.user.service;

import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userService {
    @Autowired
    private userRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<User> getUsers() {
        return  userRepo.findAll();
    }

    public User addUser(User user) {
        if(user.getRole()==null){
            user.setRole("USER");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User updateUser(User user, Integer id) {
        return userRepo.save(user);
    }
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User addAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");
        return userRepo.save(user);
    }
}
