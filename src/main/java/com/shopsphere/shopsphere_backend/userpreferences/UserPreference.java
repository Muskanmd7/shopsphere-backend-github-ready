package com.shopsphere.shopsphere_backend.userpreferences;

import com.shopsphere.shopsphere_backend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_preference")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    // Personal Information
    private String gender;

    private Integer age;

    private Double height;      // cm

    private Double weight;      // kg

    // Body Measurements (optional)
    private Double bustSize;

    private Double waistSize;

    private Double hipSize;

    // Beauty Preferences
    private String skinType;

    private String skinConcern;

    private String undertone;

    private String hairType;

    // Fashion Preferences
    private String bodyType;

    private String preferredFit;

    private String favoriteColor;

    private String clothingSize;

    // Shopping Preferences
    private String favoriteCategory;

    private String favoriteBrand;

    private String budget;

    // Lifestyle
    private String occasion;

    private String preferredStyle;
}