package com.example.control_hospital.repositories;

import com.example.control_hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByNomContains(String kw, Pageable pageable);

    void deleteById(Long id);




    //--------------------------------------------------------
    Patient findByNom(String nom);
    List<Patient> findAll();

    Optional<Patient> findById(Long id);
    List<Patient> searchByNom(String nom);

    //List<Patient> getAllPatients();


 /*   public List<Patient> findByMalade(boolean m);
    Page<Patient> findByMalade(boolean m, Pageable pageable);
    List<Patient> findByMaladeAndScoreLessThan(boolean m, int score);
    List<Patient> findByMaladeIsTrueAndScoreLessThan( int score);
    List<Patient> findByDateNaissanceBetween(Date d1, Date d2);

    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z ")
    List<Patient> chercherpatients (@Param("x") Date d1,@Param("x") Date d2,@Param("z") String nom);*/

}
