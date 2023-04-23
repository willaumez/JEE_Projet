package com.example.control_hospital.web;

import com.example.control_hospital.entities.Patient;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.PatientRepository;
import com.example.control_hospital.service.IHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Controller
@AllArgsConstructor
public class PatientsController {

    private PatientRepository patientRepository;
    private IHospitalService iHospitalService;
    private ConsultationRepository consultationRepository;

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping(path = "/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "8") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients= patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("ListPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam("id") Long patientId, String keyword, int page) {
        patientRepository.deleteById(patientId);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient>listPatients(){
        return patientRepository.findAll();
    }


    @GetMapping(path = "/formPatient")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "formPatient";
    }



    //-----------------------------------------------------------------------------------------------------//

    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient newPatient = iHospitalService.addPatient(patient);
        return ResponseEntity.ok().body(newPatient);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        Optional<Patient> optionalPatient = iHospitalService.getPatientById(patientId);
        return optionalPatient.map(patient -> ResponseEntity.ok().body(patient)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patients/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam("keyword") String keyword) {
        List<Patient> patients = iHospitalService.searchPatients(keyword);
        if (!patients.isEmpty()) {
            return ResponseEntity.ok().body(patients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long patientId,
                                                 @Valid @RequestBody Patient patientDetails) {
        Patient updatedPatient = iHospitalService.updatePatient(patientId, patientDetails);
        return ResponseEntity.ok(updatedPatient);
    }





}
