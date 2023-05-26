package com.example.springcrudsample.repository;

import com.example.springcrudsample.domain.entity.Location;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Tag("IntegrationTest")
@DisplayName("Location Repository Integration Tests")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    private Location location;
    private long initialCount;

    @BeforeEach
    public void init() {
        location = new Location();
        location.setStateProvince("tehran");
        location.setCity("pardis");
        location.setPostalCode("1265006859");

        initialCount = locationRepository.count();
        locationRepository.save(location);
    }

    @AfterEach
    public void teardown() {
        locationRepository.delete(location);
    }

    @Test
    @DisplayName("when deleteById from repository, then deleting should be successful")
    public void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {
        locationRepository.deleteById(location.getId());

        assertEquals(initialCount + 1, locationRepository.count());
    }

}
