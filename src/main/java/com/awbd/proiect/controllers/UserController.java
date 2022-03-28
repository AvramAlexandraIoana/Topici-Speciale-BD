package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.*;
import com.awbd.proiect.dto.TripRequest;
import com.awbd.proiect.dto.UserRolesRequest;
import com.awbd.proiect.dto.UserTripRequest;
import com.awbd.proiect.services.LocationService;
import com.awbd.proiect.services.RoleService;
import com.awbd.proiect.services.TripService;
import com.awbd.proiect.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private TripService tripService;
    private RoleService roleService;



    public UserController(UserService userService, TripService tripService, RoleService roleService) {
        this.userService = userService;
        this.tripService = tripService;
        this.roleService = roleService;
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserTripRequest userTripRequest) {
        List<Trip> trips = findTrips(userTripRequest.getTripIds());

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.update(id, trips));
    }


    private List<Trip> findTrips(List<Long> tripsIds) {
        if (tripsIds == null || tripsIds.isEmpty()) {
            return null;
        }
        return tripsIds.stream().map(tripService::findById).collect(Collectors.toList());
    }

    @PutMapping("/roles/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateRoles(@PathVariable Long id, @RequestBody @Valid UserRolesRequest userRolesRequest) {
        List<Role> roles = findRoles(userRolesRequest.getRoleIds());

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateRoles(id, roles));
    }

    private List<Role> findRoles(List<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return null;
        }
        return roleIds.stream().map(roleService::findById).collect(Collectors.toList());
    }



}
