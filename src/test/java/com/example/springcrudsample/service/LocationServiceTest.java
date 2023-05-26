package com.example.springcrudsample.service;

import com.example.springcrudsample.domain.dto.LocationDTO;
import com.example.springcrudsample.domain.entity.Location;
import com.example.springcrudsample.repository.LocationRepository;
import com.example.springcrudsample.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Location Service Unit Tests")
public class LocationServiceTest {

    @Autowired
    private ModelMapper modelMapper;
    private LocationRepository locationRepository;
    private LocationService locationService;

    @BeforeAll
    public void init() {
        locationRepository = mock(LocationRepository.class);
        locationService = new LocationServiceImpl(locationRepository);
    }

    @Test
    @DisplayName("given Location id, when get Location, then Location is retrieved")
    void givenBookId_whenGetBook_ThenBookRetrieved() {

        //given
        long existingBookId = 0L;
        Location location = new Location();
        location.setId(0L);
        location.setStateProvince("tehran");
        location.setCity("tehran");
        location.setPostalCode("1234567890");
        when(locationRepository.findById(existingBookId)).thenReturn(Optional.of(location));

        //when
        LocationDTO locationDTO = modelMapper.map(locationService.findOne(existingBookId).orElse(null), LocationDTO.class);

        //then
        assertNotNull(location);
        assertNotNull(location.getId());
        assertEquals(locationDTO.getId(), location.getId());
    }

}
