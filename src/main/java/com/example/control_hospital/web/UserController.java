package com.example.control_hospital.web;

import com.example.control_hospital.entities.Role;
import com.example.control_hospital.entities.User;
import com.example.control_hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/user/{username}")
    public User userByUserName(@PathVariable String username){
        return userService.findUserByUserName(username);
    }

    @GetMapping("/users")
    public List<User> Users(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/roles")
    public List<Role> Roles(){
        return userService.getAllRoles();
    }

    @PostMapping("/users/addUser")
    public User addUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @PostMapping("/users/addRole")
    public Role addRole(@RequestBody Role role){
        return userService.addNewRole(role);
    }

    @GetMapping("/users/role/{rolename}")
    public Role roleByUserName(@PathVariable String rolename){
        return userService.findRoleByRoleName(rolename);
    }

    @PostMapping("/users/{userName}/roles/{roleName}")
    public ResponseEntity<Void> addRoleToUser(
            @PathVariable String userName,
            @PathVariable String roleName) {

        userService.addRoleToUser(userName, roleName);
        return ResponseEntity.ok().build();
    }






}
