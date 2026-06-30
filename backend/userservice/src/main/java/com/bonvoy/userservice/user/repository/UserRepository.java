package com.bonvoy.userservice.user.repository;

import com.bonvoy.userservice.user.entity.User;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findById(UUID id);
}
