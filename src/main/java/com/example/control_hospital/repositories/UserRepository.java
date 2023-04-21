package com.example.control_hospital.repositories;


import com.example.control_hospital.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String userName);
    List<User> findAll();

}
