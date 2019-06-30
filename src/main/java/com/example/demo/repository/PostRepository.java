package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findAllByTitle(String title, Pageable pageRequest);

    @Query("SELECT p FROM Post p join Location l on p.location = l.id WHERE l.city = :city")
    Page<Post> findAllByCity(@Param("city") String city, Pageable pageRequest);
}
