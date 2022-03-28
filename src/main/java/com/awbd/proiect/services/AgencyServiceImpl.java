package com.awbd.proiect.services;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.repositories.AgencyRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    private static final Logger logger = LoggerFactory.getLogger(AgencyServiceImpl.class);

    AgencyRepository agencyRepository;
    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<Agency> findAll(){
        List<Agency> agencyList = new LinkedList<>();
        agencyRepository.findAll().iterator().forEachRemaining(agencyList::add);
        logger.info("S-au preluat agentiile {}", agencyList);
        return agencyList;
    }

    @Override
    public Agency findById(Long l) {
        Optional<Agency> agencyOptional =
                agencyRepository.findById(l);
        if (!agencyOptional.isPresent()) {
            throw new ObjectNotFoundException("Agentia nu a fost gasita!");
        }
        logger.info("S-a preluat agentia cu id-ul " + l + " {}", agencyOptional.get());
        return agencyOptional.get();
    }

    @Override
    public Agency save(Agency agency) {
        Agency savedAgency = agencyRepository.save(agency);
        logger.info("S-a adaugat agentia {}", savedAgency);
        return savedAgency;
    }

    @Override
    public
    Agency update(Agency agency) {
        Optional<Agency> agencyOptional =
                agencyRepository.findById(agency.getId());
        if (!agencyOptional.isPresent()) {
            throw new RuntimeException("Agentia nu a fost gasita!");
        }
        Agency updatedAgency = agencyRepository.save(agency);
        logger.info("S-au updatat agentia cu id-ul " + updatedAgency.getId() + " {}", updatedAgency);
        return updatedAgency;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("S-a sters agentia cu id-ul " + id);
        agencyRepository.deleteById(id);
    }

    @Override
    public List<Agency> findByLocation(Long locationId){
        List<Agency> agencyList = agencyRepository.findByLocation(locationId);
        logger.info("S-au preluat agentiile cu locationId egal cu  " + locationId + " {}", agencyList);
        return agencyList;
    }





}
