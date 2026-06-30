package com.bonvoy.userservice.user.service.impl;

import com.bonvoy.userservice.common.exception.UserAlreadyExistsException;
import com.bonvoy.userservice.common.exception.UserNotFoundException;
import com.bonvoy.userservice.user.dto.UserRequest;
import com.bonvoy.userservice.user.dto.UserResponse;
import com.bonvoy.userservice.user.entity.User;
import com.bonvoy.userservice.user.enums.Role;
import com.bonvoy.userservice.user.enums.UserStatus;
import com.bonvoy.userservice.user.repository.UserRepository;
import com.bonvoy.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        validate(userRequest);
        User user = builderUser(userRequest);
        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("No user found with id: "+ id));
        return mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse).toList();
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.email()).orElseThrow(()->new UserNotFoundException("No user found with email: "+ userRequest.email()));
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhoneNumber(userRequest.phoneNumber());

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User savedUser) {
        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }

    private User builderUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .phoneNumber(userRequest.phoneNumber())
                .password(userRequest.password())
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void validate(UserRequest userRequest){
        if(userRepository.findByEmail(userRequest.email()).isPresent()){
            throw new UserAlreadyExistsException("User already exists with email: " + userRequest.email());
        }
        if (userRepository.findByPhoneNumber(userRequest.phoneNumber()).isPresent()){
            throw new UserAlreadyExistsException("User already exists with phone number: " + userRequest.phoneNumber());
        }
    }
}
