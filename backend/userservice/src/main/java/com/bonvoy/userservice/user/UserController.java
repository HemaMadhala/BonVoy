package com.bonvoy.userservice.user;

import com.bonvoy.userservice.user.dto.UserRequest;
import com.bonvoy.userservice.user.dto.UserResponse;
import com.bonvoy.userservice.user.entity.User;
import com.bonvoy.userservice.user.service.UserService;
import com.bonvoy.userservice.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id){
        UserResponse user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        UserResponse user = userService.updateUser(userRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}
