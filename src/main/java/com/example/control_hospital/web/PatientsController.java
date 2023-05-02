package com.example.control_hospital.web;

import com.example.control_hospital.entities.Patient;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientsController {

    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping(path = "/user/index")
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

    @GetMapping(path = "/admin/delete")
    public String delete(@RequestParam("id") Long patientId, String keyword, int page) {
        patientRepository.deleteById(patientId);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/user/patients")
    @ResponseBody
    public List<Patient>listPatients(){
        return patientRepository.findAll();
    }


    @GetMapping(path = "/admin/formPatient")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @GetMapping(path = "/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient= patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("Patient Introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }


    @PostMapping("/admin/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    //-----------------------------------------------------------------------------------------------------//

    /*@PostMapping("/patients")
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
    }*/





}
