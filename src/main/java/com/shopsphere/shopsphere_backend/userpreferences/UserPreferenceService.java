package com.shopsphere.shopsphere_backend.userpreferences;
import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.user.Repository.userRepo;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreferenceRepo;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceService {
    @Autowired
    private UserPreferenceRepo userPreferenceRepo;

    @Autowired
    private userRepo userRepo;

    // Save
    public UserPreference savePreference(String username, UserPreference preference) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<UserPreference> existing =
                userPreferenceRepo.findByUserId(user.getId());

        if (existing.isPresent()) {
            preference.setId(existing.get().getId()); // Update existing record
        }

        preference.setUser(user);

        return userPreferenceRepo.save(preference);
    }

    // Get
    public UserPreference getPreference(String username) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userPreferenceRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));
    }

    // Delete
    public void deletePreference(String username) {

        UserPreference preference = getPreference(username);

        userPreferenceRepo.delete(preference);
    }
}
