package com.example.control_hospital.web;

import com.example.control_hospital.entities.Medecin;
import com.example.control_hospital.entities.Patient;
import com.example.control_hospital.entities.RendezVous;
import com.example.control_hospital.entities.StatusRDV;
import com.example.control_hospital.repositories.MedecinRepository;
import com.example.control_hospital.repositories.PatientRepository;
import com.example.control_hospital.repositories.RendezVousRepository;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@AllArgsConstructor
public class RendezVousController {

    private RendezVousRepository rendezVousRepository;
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;

    @GetMapping(path = "/user/rendezvous")
    public String rendezvous(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size,
                             @RequestParam(name = "dateSearch", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateSearch) {

        Page<RendezVous> pageRendezvous;
        if (dateSearch != null && !dateSearch.equals("")) {
            pageRendezvous = rendezVousRepository.findByDate(dateSearch, PageRequest.of(page, size));
        } else {
            pageRendezvous = rendezVousRepository.findAll(PageRequest.of(page, size));
        }

        model.addAttribute("ListRendezVous", pageRendezvous.getContent());
        model.addAttribute("pages", new int[pageRendezvous.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", dateSearch);
        return "rendezvous";
    }

    @GetMapping(path = "/admin/rendezvousDelete")
    public String rendezvousDelete(@RequestParam("id") String rdvId, @RequestParam("keyword") String keyword, @RequestParam("page") int page) {
        Long id = Long.valueOf(rdvId);
        rendezVousRepository.deleteById(id);
        return "redirect:/user/rendezvous?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/user/allRendezvous")
    @ResponseBody
    public List<RendezVous> listRendezvous(){
        return rendezVousRepository.findAll();
    }


    @GetMapping(path = "/admin/formRendezvous")
    public String formRendezvous(Model model){
        List<Patient> listPatients= patientRepository.findAll();
        List<Medecin> listMedecins= medecinRepository.findAll();

        // trier les listes par ordre alphabétique
        Collections.sort(listPatients, Comparator.comparing(Patient::getNom));
        Collections.sort(listMedecins, Comparator.comparing(Medecin::getNom));

        model.addAttribute("medecins", listMedecins);
        model.addAttribute("patients", listPatients);
        model.addAttribute("rendezvous", new RendezVous());
        return "formRendezvous";
    }

    @PostMapping("/admin/saveRendezvous")
    public String saveRendezvous(@Valid @ModelAttribute("rendezvous") RendezVous rendezVous, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formRendezvous";
        }

        Medecin medecin = medecinRepository.findById(rendezVous.getMedecin().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid medecin Id :" + rendezVous.getMedecin().getId()));

        Patient patient = patientRepository.findById(rendezVous.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + rendezVous.getPatient().getId()));

        rendezVous.setId(UUID.randomUUID().toString());
        rendezVous.setDate(new Date());
        rendezVous.setStatus(StatusRDV.PENDING);
        rendezVous.setConsultation(null);
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        rendezVousRepository.save(rendezVous);

        return "redirect:/user/rendezvous";
    }




    /*@PostMapping("/saveRendezvous")
    public String saveRendezvous(Model model, @RequestParam("patient") String patientId,
                                 @RequestParam("medecin") String medecinId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formRendezvous";

        RendezVous rendezVous = new RendezVous();

        Medecin medecin = medecinRepository.findById(Long.valueOf(medecinId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid medecin Id :" + medecinId));

        Patient patient = patientRepository.findById(Long.valueOf(patientId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));

        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        rendezVous.setDate(new Date());
        rendezVous.setStatus(StatusRDV.PENDING);
        rendezVousRepository.save(rendezVous);
        return "redirect:/rendezvous";

    }
*/
    /*Stream.of(StatusRDV.PENDING, StatusRDV.DONE, StatusRDV.CANCELED)
            .forEach(status ->{
        Medecin medecin=medecinRepository.findByNom("Yasmine");
        Patient patient=patientRepository.findByNom("Mohamed");
        RendezVous rendezVous= new RendezVous();
        rendezVous.setDate(new Date());
        rendezVous.setStatus(status);
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        RendezVous rendezVous1= hospitalService.saveRendezVous(rendezVous);
        System.out.println(rendezVous1.getId());
    });*/


   /* public ResponseEntity<Void> addRoleToUser(
            @PathVariable String userName,
            @PathVariable String roleName) {

        userService.addRoleToUser(userName, roleName);
        return ResponseEntity.ok().build();
    }*/


    //-----------------------------------------------------------------------------------------------------//





}
