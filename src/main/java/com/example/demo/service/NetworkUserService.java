package com.example.demo.service;

import com.example.demo.dto.NetworkUserDTO;
import com.example.demo.entity.NetworkUser;
import com.example.demo.entity.UserDetails;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.NetworkUserRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.request.NetworkUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NetworkUserService {
    private final UserDetailsRepository userDetailsRepository;
    private final NetworkUserRepository repository;
    private final LocationService locationService;

    public NetworkUserService(UserDetailsRepository userDetailsRepository,
                              NetworkUserRepository repository,
                              LocationService locationService) {
        this.userDetailsRepository = userDetailsRepository;
        this.repository = repository;
        this.locationService = locationService;
    }

    public NetworkUserDTO getNetworkUserById(long id) {
        return repository.findById(id)
                .map(NetworkUserDTO::new)
                .orElseThrow(UserNotFoundException::new);
    }

    public Page<NetworkUserDTO> getAllNetworkUsers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(NetworkUserDTO::new);
    }

    public void deleteNetworkUserById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public NetworkUserDTO createNetworkUser(NetworkUserRequest req) {
        var userDetails = new UserDetails(req.getEmail(), req.getPassword());
        var saved = userDetailsRepository.save(userDetails);
        var location = locationService.findOrCreateLocation(req.getLocation());
        var dateCreated = LocalDateTime.now();
        var networkUser = new NetworkUser(saved.getId(),
                req.getNickname(),
                req.getFirstName(),
                req.getLastName(),
                dateCreated,
                location);
        return new NetworkUserDTO(repository.save(networkUser));
    }

    @Transactional
    public NetworkUserDTO updateNetworkUser(long id, NetworkUserRequest req) {
        var networkUser = findById(id);

        var location = locationService.findOrCreateLocation(req.getLocation());

        networkUser.setNickname(req.getNickname());
        networkUser.setFirstName(req.getFirstName());
        networkUser.setLastName(req.getLastName());
        networkUser.setLocation(location);

        return new NetworkUserDTO(networkUser);
    }

    public Page<NetworkUserDTO> getAllNetworkUsersByCity(PageRequest pageRequest, String city) {
        return repository.findAllByCity(city, pageRequest)
                .map(NetworkUserDTO::new);
    }

    public NetworkUser findById(long id) {
        return repository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
