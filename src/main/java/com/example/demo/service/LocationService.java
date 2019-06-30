package com.example.demo.service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.request.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public Location findOrCreateLocation(LocationRequest location) {
        if (location == null) {
            return null;
        }

        log.debug("finding location by coordinates {} {}", location.getLongitude(), location.getLatitude());

        return repository.findByLongitudeAndLatitude(location.getLongitude(), location.getLatitude())
                .orElseGet(() -> {
                    var loc = new Location(location.getLongitude(),
                            location.getLatitude(),
                            location.getCity(),
                            location.getCountry());
                    log.info("creating new location {}", loc);
                    return repository.save(loc);
                });
    }
}
