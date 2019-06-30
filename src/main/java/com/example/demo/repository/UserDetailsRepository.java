package com.example.demo.repository;

import com.example.demo.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    Optional<UserDetails> findByEmail(String username);
}
