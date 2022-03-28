package com.awbd.proiect.services;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);


}
