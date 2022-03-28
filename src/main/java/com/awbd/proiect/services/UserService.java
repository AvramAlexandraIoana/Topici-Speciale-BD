package com.awbd.proiect.services;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Role;
import com.awbd.proiect.domain.Trip;
import com.awbd.proiect.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User update(Long id, List<Trip> trips);
    User updateRoles(Long id, List<Role> roles);

}
