package com.example.demo.api;

import com.example.demo.dto.NetworkUserDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.NetworkUserService;
import com.example.demo.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final PostService postService;
    private final NetworkUserService networkUserService;

    public SearchController(PostService postService, NetworkUserService networkUserService) {
        this.postService = postService;
        this.networkUserService = networkUserService;
    }

    @GetMapping("post/title/{title}")
    public Page<PostDTO> getAllPostsByTitle(@RequestParam(name = "page") int page,
                                            @RequestParam(name = "size") int size,
                                            @PathVariable("title") String title) {
        var pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("dateCreated")));
        return postService.getAllPostsWithTitle(pageRequest, title);
    }

    @GetMapping("post/location/{city}")
    public Page<PostDTO> getAllPostsByLocation(@RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size,
                                               @PathVariable("city") String city) {
        var pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("dateCreated")));
        return postService.getAllPostsByLocation(pageRequest, city);
    }

    @GetMapping("user/nickname/{nickname}")
    public NetworkUserDTO getAllUsersByTitle(@PathVariable("nickname") String nickname) {
        return networkUserService.getAllUsersWithNickname(nickname);
    }

    @GetMapping("user/location/{city}")
    public Page<NetworkUserDTO> getAllUsersByLocation(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size,
                                                      @PathVariable("city") String city) {
        var pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("dateCreated")));
        return networkUserService.getAllNetworkUsersByCity(pageRequest, city);
    }
}
