package com.example.demo.api;

import com.example.demo.dto.PostDTO;
import com.example.demo.request.PostRequest;
import com.example.demo.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PostDTO> getAllPosts(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size) {
        var pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("dateCreated")));
        return service.getAllPosts(pageRequest);
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
    public PostDTO updatePost(@PathVariable long id, @Valid @RequestBody PostRequest req) {
        return service.updatePost(id, req);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        service.deletePostById(id);
    }
}
