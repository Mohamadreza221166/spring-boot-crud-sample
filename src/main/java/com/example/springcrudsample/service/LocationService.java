package com.example.springcrudsample.service;

import com.example.springcrudsample.domain.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location save(Location location);

    Location update(Location location);

    List<Location> findAll();

    Optional<Location> findOne(Long id);

    void delete(Long id);
}
