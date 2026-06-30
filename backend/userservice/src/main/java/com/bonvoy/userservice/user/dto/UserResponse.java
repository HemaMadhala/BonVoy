package com.bonvoy.userservice.user.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
