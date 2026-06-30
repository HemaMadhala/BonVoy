package com.bonvoy.userservice.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,
        @Pattern(
                regexp = "^[6-9][0-9]{9}$",
                message = "Phone number must contain exactly 10 digits"
        )
        String phoneNumber,
        @NotBlank(message = "Password is required")
        String password
) {
}
