package com.example.control_hospital.web;

import com.example.control_hospital.entities.RendezVous;
import com.example.control_hospital.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class RendezVousController {

    private RendezVousRepository rendezVousRepository;

    @GetMapping(path = "/rendezvous")
    public String rendezvous(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "8") int size,
                             @RequestParam(name = "keyword", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date keyword) {
        Page<RendezVous> pageRendezvous = rendezVousRepository.findByDate(keyword, PageRequest.of(page, size));
        model.addAttribute("ListRendezVous", pageRendezvous.getContent());
        model.addAttribute("pages", new int[pageRendezvous.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "rendezvous";
    }

    @GetMapping(path = "/rendezvousDelete")
    public String rendezvousDelete(@RequestParam("id") Long rdvId, String keyword, int page) {
        rendezVousRepository.deleteById(rdvId);
        return "redirect:/rendezvous?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/allRendezvous")
    @ResponseBody
    public List<RendezVous> listRendezvous(){
        return rendezVousRepository.findAll();
    }


    @GetMapping(path = "/formRendezvous")
    public String formRendezvous(Model model){
        model.addAttribute("rendezvous", new RendezVous());
        return "formRendezvous";
    }

    @PostMapping("/saveRendezvous")
    public String saveRendezvous(Model model, @Valid RendezVous rendezVous, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formRendezvous";
        rendezVousRepository.save(rendezVous);
        return "redirect:/rendezvous";
    }



    //-----------------------------------------------------------------------------------------------------//





}
