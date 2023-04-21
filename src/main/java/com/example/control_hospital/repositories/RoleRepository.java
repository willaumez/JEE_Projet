package com.example.control_hospital.repositories;


import com.example.control_hospital.entities.Role;
import com.example.control_hospital.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
