package com.example.demo.api;

import com.example.demo.dto.PostDTO;
import com.example.demo.request.PostRequest;
import com.example.demo.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PostDTO> getAllPosts(Pageable page) {
        return service.getAllPosts(page);
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable long id) {
        return service.getPostById(id);
    }

    @PostMapping
    public PostDTO createPost(@Valid @RequestBody PostRequest req, Authentication auth) {
        return service.createPost(req, auth.getName());
    }

    @PutMapping("/{id}")
    public PostDTO createPost(@PathVariable long id, @Valid @RequestBody PostRequest req) {
        return service.updatePost(id, req);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        service.deletePostById(id);
    }
}
