package com.example.control_hospital.service;

import com.example.control_hospital.entities.Role;
import com.example.control_hospital.entities.User;
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
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user= this.findUserByUserName(userName);
        Role role= this.findRoleByRoleName(roleName);
        if (user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
        User user= userRepository.findByUserName(userName);
        if (user==null)
            throw new RuntimeException("Bad credentials");

         if (user.getUserPassword().equals(password)){
             return user;
         }
         throw new RuntimeException("Bad credentials");
    }
}
