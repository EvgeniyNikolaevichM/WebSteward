package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.resInterface;
import com.Web.WebSteward.interfaces.tOiRInterface;
import com.Web.WebSteward.models.Res;
import com.Web.WebSteward.models.TOiR;
import com.Web.WebSteward.services.ResService;
import com.Web.WebSteward.services.TOiRService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/TOiR")
public class TOiRController {

    private final TOiRService toirService;
    @Autowired
    public TOiRController(TOiRService toirService) {
        this.toirService = toirService;
    }
    @Autowired
    private tOiRInterface tOiRInterface;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("TOiR", toirService.findAll());
        return "TOiR/index";
    }

    @GetMapping("/{id}")
    public String TOiRDetails(@PathVariable(value = "id") long id, Model model) {
        if(!tOiRInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<TOiR> TOiR = tOiRInterface.findById(id);
        ArrayList<TOiR> ressp = new ArrayList<>();
        TOiR.ifPresent(ressp::add);
        model.addAttribute("TOiR", ressp);
        return "TOiR/show";
    }

    @GetMapping("/new")
    public String newTOiR(@ModelAttribute("TOiR") TOiR TOiR) {
        return "TOiR/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("TOiR") @Valid TOiR TOiR,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "TOiR/new";
        toirService.save(TOiR);
        return "redirect:/TOiR";
    }

    @GetMapping("/{id}/edit")
    public String TOiREdit(@PathVariable(value = "id") long id, Model model) {
        if(!tOiRInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<TOiR> TOiR = tOiRInterface.findById(id);
        ArrayList<TOiR> toir = new ArrayList<>();
        TOiR.ifPresent(toir::add);
        model.addAttribute("TOiR", toir);
        return "TOiR/edit";
    }

    @PostMapping("/{id}/edit")
    public String TOiREditUpdate(@PathVariable(value = "id") long id, @RequestParam String klassOborud,
                                @RequestParam String VidRabot, @RequestParam String Date, Model model, @ModelAttribute("TOiR") @Valid TOiR toir, BindingResult bindingResult){
        TOiR TOiR = tOiRInterface.findById(id).orElseThrow();
        TOiR.setKlassOborud(klassOborud);
        TOiR.setVidRabot(VidRabot);
        TOiR.setDate(Date);
        if (bindingResult.hasErrors())
            return "res/edit";
        tOiRInterface.save(TOiR);
        return "redirect:/TOiR";
    }

    @PostMapping("/{id}/remove")
    public String TOiRDelete(@PathVariable(value = "id") long id, Model model){
        TOiR TOiR = tOiRInterface.findById(id).orElseThrow();
        tOiRInterface.delete(TOiR);
        return "redirect:/res";
    }
}