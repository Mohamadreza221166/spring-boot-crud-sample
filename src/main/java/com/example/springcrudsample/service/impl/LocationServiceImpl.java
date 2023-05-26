package com.example.springcrudsample.service.impl;

import com.example.springcrudsample.domain.entity.Location;
import com.example.springcrudsample.repository.LocationRepository;
import com.example.springcrudsample.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location save(Location location) {
        log.debug("سرویس ثبت مکان جدید: {}", location);
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) {
        log.debug("سرویس ویرایش اطلاعات مکان: {}", location);
        return locationRepository.save(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAll() {
        log.debug("سرویس دریافت لیست مکان ها");
        return locationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Location> findOne(Long id) {
        log.debug("سرویس دریافت اطلاعات یک مکان: {}", id);
        return locationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("سرویس حذف یک مکان: {}", id);
        locationRepository.deleteById(id);
    }
}
