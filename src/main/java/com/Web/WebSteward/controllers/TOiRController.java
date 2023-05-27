package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.tOiRInterface;
import com.Web.WebSteward.models.TOiR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class TOiRController {

    @Autowired
    private tOiRInterface TOiRInterface;

    @GetMapping("/TOiR")
    public String TOiR(Model model) {
        Iterable<TOiR> TOiR = TOiRInterface.findAll();
        model.addAttribute("TOiR", TOiR);
        return "/TOiR/TOiR";
    }

    @GetMapping("/TOiRAdd")
    public String TOiRAdd(Model model) {
        return "/TOiR/TOiRAdd";
    }

    @PostMapping("/TOiRAdd")
    public String newTOiRAdd(@RequestParam String KlassOborud, @RequestParam String VidRabot, @RequestParam String Date, Model model){
        TOiR TOiR = new TOiR(KlassOborud, VidRabot, Date);
        TOiRInterface.save(TOiR);
        return "redirect:/TOiR";
    }

    @GetMapping("/TOiR/{id}")
    public String TOiRDetails(@PathVariable(value = "id") long id, Model model) {
        if(!TOiRInterface.existsById(id)){
            return "redirect:/TOiR";
        }
        Optional<TOiR> TOiR = TOiRInterface.findById(id);
        ArrayList<TOiR> ressp = new ArrayList<>();
        TOiR.ifPresent(ressp::add);
        model.addAttribute("TOiR", ressp);
        return "/TOiR/TOiRDetails";
    }

    @GetMapping("/TOiR/{id}/edit")
    public String TOiREdit(@PathVariable(value = "id") long id, Model model) {
        if(!TOiRInterface.existsById(id)){
            return "redirect:/TOiR";
        }
        Optional<TOiR> TOiR = TOiRInterface.findById(id);
        ArrayList<TOiR> res = new ArrayList<>();
        TOiR.ifPresent(res::add);
        model.addAttribute("TOiR", res);
        return "/TOiR/TOiREdit";
    }

    @PostMapping("/TOiR/{id}/edit")
    public String TOiREditUpdate(@PathVariable(value = "id") long id, @RequestParam String KlassOborud, @RequestParam String VidRabot, @RequestParam String Date, Model model){
        TOiR TOiR = TOiRInterface.findById(id).orElseThrow();
        TOiR.setKlassOborud(KlassOborud);
        TOiR.setVidRabot(VidRabot);
        TOiR.setDate(Date);
        TOiRInterface.save(TOiR);
        return "redirect:/TOiR";
    }

    @PostMapping("/TOiR/{id}/remove")
    public String TOiRDelete(@PathVariable(value = "id") long id, Model model){
        TOiR TOiR = TOiRInterface.findById(id).orElseThrow();
        TOiRInterface.delete(TOiR);
        return "redirect:/TOiR";
    }
}