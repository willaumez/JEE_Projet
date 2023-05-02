package com.example.control_hospital.repositories;


import com.example.control_hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
    List<AppUser> findAll();

}
