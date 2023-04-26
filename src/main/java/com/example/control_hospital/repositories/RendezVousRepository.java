package com.example.control_hospital.repositories;

import com.example.control_hospital.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Date> {
    Page<RendezVous> findByDateContains(Date kw, Pageable pageable);

    Page<RendezVous> findByDate(Date keyword, Pageable pageable);

    List<RendezVous> findAll();

    Optional<RendezVous> findById(Long id);


    void deleteById(Long id);
}
