package com.example.control_hospital.web;

import com.example.control_hospital.entities.Medecin;
import com.example.control_hospital.entities.Patient;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.MedecinRepository;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MedecinsController {

    private MedecinRepository medecinRepository;

    @GetMapping(path = "/medecins")
    public String medecins(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "8") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Medecin> pageMedecin= medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("ListMedecins", pageMedecin.getContent());
        model.addAttribute("pages", new int[pageMedecin.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "medecins";
    }

    @GetMapping(path = "/medecinDelete")
    public String medecinDelete(@RequestParam("id") Long medecinId, String keyword, int page) {
        medecinRepository.deleteById(medecinId);
        return "redirect:/medecins?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/allMedecins")
    @ResponseBody
    public List<Medecin>listMedecins(){
        return medecinRepository.findAll();
    }


    @GetMapping(path = "/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "formMedecin";
    }

    @PostMapping("/saveMedecin")
    public String saveMedecin(Model model, @Valid Medecin medecin, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/medecins";
    }



    //-----------------------------------------------------------------------------------------------------




}
