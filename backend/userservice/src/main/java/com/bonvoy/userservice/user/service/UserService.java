package com.bonvoy.userservice.user.service;

import com.bonvoy.userservice.user.dto.UserRequest;
import com.bonvoy.userservice.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUser(UUID id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(UserRequest userRequest);
}
