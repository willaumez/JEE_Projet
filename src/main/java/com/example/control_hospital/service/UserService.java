package com.example.control_hospital.service;

import com.example.control_hospital.security.entities.AppRole;
import com.example.control_hospital.security.entities.AppUser;

import java.util.List;

public interface UserService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);

    List<AppUser> getAllUsers();

    List<AppRole> getAllRoles();

    AppUser findUserByUserName(String userName);
    AppRole findRoleByRoleName(String roleName);
    void addRoleToUser(String userName, String roleName);
    AppUser authenticate(String userName, String password);

}
