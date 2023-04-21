package com.example.control_hospital.service;

import com.example.control_hospital.entities.Role;
import com.example.control_hospital.entities.User;

import java.util.List;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName, String roleName);
    User authenticate(String userName, String password);

}
