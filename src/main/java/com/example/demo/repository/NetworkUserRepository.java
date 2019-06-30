package com.example.demo.repository;

import com.example.demo.entity.NetworkUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NetworkUserRepository extends PagingAndSortingRepository<NetworkUser, Long> {
    @Query("SELECT u FROM NetworkUser u join Location l on u.location = l.id WHERE l.city = :city")
    Page<NetworkUser> findAllByCity(@Param("city") String city, Pageable pageRequest);
}
