package com.shopsphere.shopsphere_backend.user.controller;

import com.shopsphere.shopsphere_backend.Jwtsecurity.JwtService;
import com.shopsphere.shopsphere_backend.user.LoginDTO;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.user.service.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }
   @PostMapping("/admin/register")
   public User addAdmin(@Valid @RequestBody User user) {
        return userService.addAdmin(user);
   }

    @PutMapping("/users/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
        return userService.updateUser(user, id);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(request.getUsername());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
