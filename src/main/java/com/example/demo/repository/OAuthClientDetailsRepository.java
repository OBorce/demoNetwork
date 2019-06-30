package com.example.demo.repository;

import com.example.demo.entity.OAuthClientDetails;
import com.example.demo.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OAuthClientDetailsRepository extends CrudRepository<OAuthClientDetails, String> {
}
