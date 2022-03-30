package com.awbd.proiect.mapper;


import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.dto.AgencyRequest;
import com.awbd.proiect.dto.AgencyUpdate;
import com.awbd.proiect.dto.LocationUpdate;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {
    public Agency agencyRequestToAgency(AgencyRequest agencyRequest) {
        return new Agency(agencyRequest.getName(), agencyRequest.getLocation(), agencyRequest.getUserId());
    }

    public Agency agencyUpdateToAgency(AgencyUpdate agencyUpdate) {
        return new Agency(agencyUpdate.getId(), agencyUpdate.getName(), agencyUpdate.getLocation(), agencyUpdate.getUserId());
    }
}
