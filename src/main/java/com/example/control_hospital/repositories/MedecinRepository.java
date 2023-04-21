package com.example.control_hospital.repositories;

import com.example.control_hospital.entities.Medecin;
import com.example.control_hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
   Medecin findByNom(String nom);
}
