package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.request.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository repository;
    private final LocationService locationService;
    private final UserDetailsService userDetailsService;

    public PostService(PostRepository repository,
                       LocationService locationService,
                       UserDetailsService userDetailsService) {
        this.repository = repository;
        this.locationService = locationService;
        this.userDetailsService = userDetailsService;
    }

    public Page<PostDTO> getAllPosts(Pageable page) {
        return repository.findAll(page)
                .map(PostDTO::new);
    }

    public PostDTO getPostById(long id) {
        return repository.findById(id)
                .map(PostDTO::new)
                .orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public PostDTO createPost(PostRequest req, String username) {
        var user = userDetailsService.findNetworkUser(username);
        var location = locationService.findOrCreateLocation(req.getLocation());
        var dateCreated = LocalDateTime.now();
        var post = new Post(req.getTitle(), req.getDescription(), dateCreated, user, location);
        var saved = repository.save(post);
        return new PostDTO(saved);
    }

    @Transactional
    public PostDTO updatePost(long postId, PostRequest req) {
        var post = repository.findById(postId).orElseThrow(PostNotFoundException::new);
        var location = locationService.findOrCreateLocation(req.getLocation());

        post.setTitle(req.getTitle());
        post.setDescription(req.getDescription());
        post.setLocation(location);

        return new PostDTO(post);
    }

    public void deletePostById(long id) {
        repository.deleteById(id);
    }
}
