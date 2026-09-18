package com.shopsphere.shopsphere_backend.userpreferences;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreference;
@RestController
@RequestMapping("/preferences")
@CrossOrigin(origins = "*")
public class UserPreferenceCon {

    @Autowired
    private UserPreferenceService preferenceService;

    // Save or Update Preferences
    @PostMapping
    public UserPreference savePreference(
            @RequestBody UserPreference preference,
            Authentication authentication) {
           System.out.println(authentication);
        return preferenceService.savePreference(authentication.getName(), preference);
    }

    // Get Logged-in User Preferences
    @GetMapping
    public UserPreference getPreference(Authentication authentication) {

        return preferenceService.getPreference(authentication.getName());
    }

    // Delete Logged-in User Preferences
    @DeleteMapping
    public ResponseEntity<String> deletePreference(Authentication authentication) {

        preferenceService.deletePreference(authentication.getName());

        return ResponseEntity.ok("Preferences deleted successfully.");
    }
}