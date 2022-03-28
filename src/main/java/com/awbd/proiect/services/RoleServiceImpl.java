package com.awbd.proiect.services;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Role;
import com.awbd.proiect.repositories.LocationRepository;
import com.awbd.proiect.repositories.RoleRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements  RoleService{
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll(){
        List<Role> roles = new LinkedList<>();
        roleRepository.findAll().iterator().forEachRemaining(roles::add);
        logger.info("S-au preluat  rolurile {}", roles);
        return roles;
    }

    @Override
    public Role findById(Long l) {
        Optional<Role> roleOptional =
                roleRepository.findById(l);
        if (!roleOptional.isPresent()) {
            throw new ObjectNotFoundException("Rolul nu a fost gasit!");
        }
        logger.info("S-a preluat rolul cu id-ul " + l + " {} ", roleOptional.get());
        return roleOptional.get();
    }
}
