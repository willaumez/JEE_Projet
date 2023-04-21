package com.example.control_hospital.service;

import com.example.control_hospital.entities.*;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.MedecinRepository;
import com.example.control_hospital.repositories.PatientRepository;
import com.example.control_hospital.repositories.RendezVousRepository;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    public HospitalServiceImpl(
            PatientRepository patientRepository, MedecinRepository medecinRepository,
            ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }

    @Override
    public List<Patient> searchPatients(String keyword) {
        return patientRepository.searchByNom(keyword);
    }

    @Override
    public Patient updatePatient(Long patientId, Patient patientDetails) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patient.setNom(patientDetails.getNom());
        patient.setAge(patientDetails.getAge());
        patient.setGenre(patientDetails.getGenre());
        final Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient).getBody();
    }

    @Override
    public Map<String, Boolean> deletePatient(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (!patientOptional.isPresent()) {
            throw new ResourceNotFoundException("Patient not found for id: " + patientId);
        }
        Patient patient = patientOptional.get();
        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    //======================================================================================//
    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }


    //======================================================================================//

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }



    //======================================================================================//

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }




    //======================================================================================//

}
