package com.shopsphere.shopsphere_backend.address;

import com.shopsphere.shopsphere_backend.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    private String fullName;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String addressLine1;

    @NotBlank
    private String addressLine2;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
     @NotBlank
    private String country;
    @NotBlank
    private String pincode;
}
