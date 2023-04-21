package com.example.control_hospital.service;

import com.example.control_hospital.entities.Consultation;
import com.example.control_hospital.entities.Medecin;
import com.example.control_hospital.entities.Patient;
import com.example.control_hospital.entities.RendezVous;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient addPatient(Patient patient);
    Optional<Patient> getPatientById(Long patientId);
    List<Patient> searchPatients(String keyword);
    Patient updatePatient(Long patientId, Patient patientDetails);
    Map<String, Boolean> deletePatient( Long patientId);


    //======================================================================================//

    Medecin saveMedecin(Medecin medecin);


    //======================================================================================//

    RendezVous saveRendezVous(RendezVous rendezVous);


    //======================================================================================//

    Consultation saveConsultation(Consultation consultation);


    //======================================================================================//


}
