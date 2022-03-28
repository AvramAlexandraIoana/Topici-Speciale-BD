package com.awbd.proiect.mapper;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.dto.CountryRequest;
import com.awbd.proiect.dto.CountryUpdate;
import com.awbd.proiect.dto.LocationRequest;
import com.awbd.proiect.dto.LocationUpdate;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location locationRequestToLocation(LocationRequest locationRequest) {
        return new Location(locationRequest.getCity(), locationRequest.getStreetAddress(), locationRequest.getCountry(), locationRequest.getInfo());
    }

    public Location locationUpdateToLocation(LocationUpdate locationUpdate) {
        return new Location(locationUpdate.getId(), locationUpdate.getCity(), locationUpdate.getStreetAddress(), locationUpdate.getCountry(), locationUpdate.getInfo());
    }
}
