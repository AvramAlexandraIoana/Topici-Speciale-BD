package com.awbd.proiect.mapper;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Trip;
import com.awbd.proiect.dto.*;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    public Trip tripRequestToTrip(TripRequest tripRequest) {
        return new Trip(tripRequest.getName(), tripRequest.getNumberOfSeats(), tripRequest.getPrice(), tripRequest.getDuration(),
                tripRequest.getStartDate(), tripRequest.getEndDate(), tripRequest.getLocation(), tripRequest.getAgency());
    }

    public Trip tripUpdateToTrip(TripUpdate tripUpdate) {
        return new Trip(tripUpdate.getId(), tripUpdate.getName(), tripUpdate.getNumberOfSeats(), tripUpdate.getPrice(), tripUpdate.getDuration(),
                tripUpdate.getStartDate(), tripUpdate.getEndDate(), tripUpdate.getLocation(), tripUpdate.getAgency());
    }


}
