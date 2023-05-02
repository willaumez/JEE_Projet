package com.example.control_hospital.service;

import com.example.control_hospital.security.entities.AppRole;
import com.example.control_hospital.security.entities.AppUser;
import com.example.control_hospital.repositories.RoleRepository;
import com.example.control_hospital.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        appUser.setUserId(UUID.randomUUID().toString());
        return userRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<AppRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public AppUser findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public AppRole findRoleByRoleName(String roleName) {
        return roleRepository.findByRole(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = this.findUserByUserName(userName);
        AppRole appRole = this.findRoleByRoleName(roleName);
        if (appUser.getAppRoles()!=null){
            appUser.getAppRoles().add(appRole);
            //appRole.getUsers().add(appUser);
        }
        //userRepository.save(user);
    }

    @Override
    public AppUser authenticate(String userName, String password) {
        AppUser appUser = userRepository.findByUsername(userName);
        if (appUser ==null)
            throw new RuntimeException("Bad credentials");

         if (appUser.getPassword().equals(password)){
             return appUser;
         }
         throw new RuntimeException("Bad credentials");
    }
}
