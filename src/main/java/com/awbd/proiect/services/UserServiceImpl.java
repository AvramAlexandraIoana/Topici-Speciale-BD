package com.awbd.proiect.services;


import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Role;
import com.awbd.proiect.domain.Trip;
import com.awbd.proiect.domain.User;
import com.awbd.proiect.repositories.UserRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UserRepository userRepository;

    @Autowired
    public  UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public List<User> findAll() {
       List<User> users = new LinkedList<>();
       userRepository.findAll().iterator().forEachRemaining(users::add);
       logger.info("S-au preluat  utilizatorii {}", users);
       return users;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException("Utilizatorul cu id-ul " + id + " nu a fost gasit!");
        }
        return userOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException("Utilizatorul nu a fost gasit!");
        }
        User user = userOptional.get();
        List<Trip> trips = new LinkedList<Trip>();
        user.getTrips().iterator().forEachRemaining(trips::add);

        for (Trip trip: trips
        ) {
            user.removeTrip(trip);
        }

        userRepository.save(user);
        userRepository.deleteById(id);
        logger.info("S-a sters utilizatorul cu id-ul " + id);

    }

    @Override
    public User update(Long id, List<Trip> trips) {
        Optional<User> userOptional =
                userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found!");
        }
        User user = userOptional.get();
        user.setTrips(trips);
        User updatedUser = userRepository.save(user);
        logger.info("S-a updatat utilizatorul cu id-ul " + updatedUser.getId() + " {}", updatedUser );
        return updatedUser;
    }

    @Override
    public User updateRoles(Long id, List<Role> roles) {
        Optional<User> userOptional =
                userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found!");
        }
        User user = userOptional.get();
        Set<Role> rolesSet = roles.stream().collect(Collectors.toSet());
        user.setRoles(rolesSet);
        User updatedUser = userRepository.save(user);
        logger.info("S-au updatat rolurile pentru utilizatorul cu id-ul " + updatedUser.getId() + " {}", updatedUser );
        return updatedUser;
    }



}
