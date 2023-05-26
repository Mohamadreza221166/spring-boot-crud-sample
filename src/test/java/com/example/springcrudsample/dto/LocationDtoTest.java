package com.example.springcrudsample.dto;


import com.example.springcrudsample.domain.dto.LocationDTO;
import com.example.springcrudsample.domain.entity.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;

@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Location Mapper Unit Tests")
public class LocationDtoTest implements Serializable {

    private static final long serialVersionUID = 1L;

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("when convert Location entity to Location dto, then correct")
    public void whenConvertLocationEntityToLocationDto_thenCorrect() {

        //given
        Location Location = new Location();
        Location.setId(1L);
        Location.stateProvince("tehran");
        Location.setCity("tehran");
        Location.setPostalCode("105698457895");
        Location.streetAddress("mirdamad street");

        //when
        LocationDTO locationDTO = modelMapper.map(Location, LocationDTO.class);

        //then
        assertEquals(locationDTO.getId(), Location.getId());
        assertEquals(locationDTO.getStateProvince(), Location.getStateProvince());
        assertEquals(locationDTO.getCity(), Location.getCity());
        assertEquals(locationDTO.getStreetAddress(), Location.getStreetAddress());
    }

    @Test
    @DisplayName("when convert Location dto to Location entity, then correct")
    public void whenConvertLocationDtoToLocationEntity_thenCorrect() {

        //given
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.stateProvince("tehran");
        locationDTO.setCity("tehran");
        locationDTO.setPostalCode("105698457895");
        locationDTO.streetAddress("mirdamad street");

        //when
        Location Location = modelMapper.map(locationDTO, Location.class);

        //then
        assertEquals(Location.getId(), locationDTO.getId());
        assertEquals(Location.getStateProvince(), locationDTO.getStateProvince());
        assertEquals(Location.getCity(), locationDTO.getCity());
        assertEquals(Location.getStreetAddress(), locationDTO.getStreetAddress());

    }

}
