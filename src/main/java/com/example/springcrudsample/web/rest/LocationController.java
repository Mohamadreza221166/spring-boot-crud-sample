package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.entity.Location;
import com.example.springcrudsample.repository.LocationRepository;
import com.example.springcrudsample.service.LocationService;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);

    private static final String ENTITY_NAME = "location";
    private final LocationService locationService;

    private final LocationRepository locationRepository;

    public LocationController(LocationService locationService, LocationRepository locationRepository) {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) throws BadRequestAlertException {
        log.debug("درخواست ایجاد مکان جدید: {}", location);
        if (location.getId() != null) {
            throw new BadRequestAlertException("مکان وارد شده قبلا در سیستم ثبت شده است.", ENTITY_NAME, Constants.ID_EXISTS);
        }
        Location result = locationService.save(location);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id", required = false) final Long id,
            @RequestBody Location location) throws BadRequestAlertException {
        log.debug("درخواست اصلاح اطلاعات مکان: {}, {}", id, location);
        if (location.getId() == null) {
            throw new BadRequestAlertException("اطلاعات وارد شده نامعتر است", ENTITY_NAME, Constants.ID_NULL);
        }
        if (!Objects.equals(id, location.getId())) {
            throw new BadRequestAlertException("اطلاعات وارد شده نامعتر است", ENTITY_NAME, Constants.ID_INVALID);
        }

        if (!locationRepository.existsById(id)) {
            throw new BadRequestAlertException("اطلاعات وارد شده نامعتر است", ENTITY_NAME, Constants.ID_NOT_FOUND);
        }

        Location result = locationService.update(location);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        log.debug("REST request to get all Locations");
        return locationService.findAll();
    }


    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        log.debug("درخواست دریافت اطلاعات مکان: {}", id);
        Location location = locationService.findOne(id).orElse(null);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        log.debug("درخواست حذف مکان: {}", id);
        locationService.delete(id);
        return ResponseEntity.ok(null);
    }
}
