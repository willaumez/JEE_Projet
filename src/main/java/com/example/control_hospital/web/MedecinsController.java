package com.example.control_hospital.web;

import com.example.control_hospital.entities.Medecin;
import com.example.control_hospital.repositories.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping(path = "/user/medecins")
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

    @GetMapping(path = "/admin/medecinDelete")
    public String medecinDelete(@RequestParam("id") Long medecinId, String keyword, int page) {
        medecinRepository.deleteById(medecinId);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/admin/allMedecins")
    @ResponseBody
    public List<Medecin>listMedecins(){
        return medecinRepository.findAll();
    }


    @GetMapping(path = "/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "formMedecin";
    }

    @GetMapping(path = "/admin/editMedecin")
    public String editMedecint(Model model, Long id, String keyword, int page){
        Medecin medecin= medecinRepository.findById(id).orElse(null);
        if (medecin==null) throw new RuntimeException("Medecin Introuvable");
        model.addAttribute("medecin", medecin);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editMedecin";
    }


    @PostMapping("/admin/saveMedecin")
    public String saveMedecin(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    /*@GetMapping("/admin/medecinDetail")
    public String medecinDetail(@RequestParam("id") Long id, Model model) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Medecin Id:" + id));

        model.addAttribute("medecin", medecin);
        model.addAttribute("rendezvous", medecin.getRendezVous());
        System.out.println("rendezvous: "+ medecin.getRendezVous());
        return "medecinDetail";
    }*/

    @GetMapping("/admin/medecinDetail")
    public String medecinDetail(@RequestParam("id") Long id, Model model) {
        Optional<Medecin> medecinOptional = medecinRepository.findById(id);
        if (medecinOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Medecin Id:" + id);
        }
        Medecin medecin = medecinOptional.get();
        model.addAttribute("medecin", medecin);
        model.addAttribute("rendezvous", medecin.getRendezVous());
        return "medecinDetail";
    }



    //-----------------------------------------------------------------------------------------------------




}
