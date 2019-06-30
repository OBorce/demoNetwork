package com.example.demo.api;

import com.example.demo.dto.NetworkUserDTO;
import com.example.demo.request.NetworkUserRequest;
import com.example.demo.service.NetworkUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final NetworkUserService service;

    public UserController(NetworkUserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<NetworkUserDTO> getAllUsersByDate(@RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size) {
        var pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("dateCreated")));
        return service.getAllNetworkUsers(pageRequest);
    }

    @GetMapping("/{id}")
    public NetworkUserDTO getUser(@PathVariable long id) {
        return service.getNetworkUserById(id);
    }

    @PostMapping
    public NetworkUserDTO createUser(@Valid @RequestBody NetworkUserRequest req) {
        return service.createNetworkUser(req);
    }

    @PutMapping("/{id}")
    public NetworkUserDTO updateUser(@PathVariable long id, @Valid @RequestBody NetworkUserRequest req) {
        return service.updateNetworkUser(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        service.deleteNetworkUserById(id);
    }
}
