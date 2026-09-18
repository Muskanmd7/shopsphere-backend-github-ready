package com.shopsphere.shopsphere_backend.userpreferences;


import com.shopsphere.shopsphere_backend.user.model.User;
import com.shopsphere.shopsphere_backend.userpreferences.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferenceRepo extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByUser(User user);

    Optional<UserPreference> findByUserId(Long userId);

    boolean existsByUser(User user);
}

