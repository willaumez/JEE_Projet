package com.example.control_hospital.repositories;

import com.example.control_hospital.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
   Medecin findByNom(String nom);
   Page<Medecin> findByNomContains(String kw, Pageable pageable);

   List<Medecin> findAll();

   Optional<Medecin> findById(Long id);
   List<Medecin> searchByNom(String nom);


   void deleteById(Long id);
}
