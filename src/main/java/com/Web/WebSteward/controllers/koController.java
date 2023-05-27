package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.koInterface;
import com.Web.WebSteward.models.KO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class koController {

    @Autowired
    private koInterface KOInterface;

    @GetMapping("/KO")
    public String KO(Model model) {
        Iterable<KO> KO = KOInterface.findAll();
        model.addAttribute("KO", KO);
        return "/KO/KO";
    }

    @GetMapping("/KOAdd")
    public String KOAdd(Model model) {
        return "/KO/KOAdd";
    }

    @PostMapping("/KOAdd")
    public String newKOAdd(@RequestParam String title, @RequestParam String klassNapr, @RequestParam String dispNaim, @RequestParam String PO, @RequestParam String SP, Model model){
        KO KO = new KO(title, klassNapr, dispNaim, PO, SP);
        KOInterface.save(KO);
        return "redirect:/KO";
    }

    @GetMapping("/KO/{id}")
    public String KODetails(@PathVariable(value = "id") long id, Model model) {
        if(!KOInterface.existsById(id)){
            return "redirect:/KO";
        }
        Optional<KO> KO = KOInterface.findById(id);
        ArrayList<KO> ressp = new ArrayList<>();
        KO.ifPresent(ressp::add);
        model.addAttribute("KO", ressp);
        return "/KO/KODetails";
    }

    @GetMapping("/KO/{id}/edit")
    public String KOEdit(@PathVariable(value = "id") long id, Model model) {
        if(!KOInterface.existsById(id)){
            return "redirect:/KO";
        }
        Optional<KO> KO = KOInterface.findById(id);
        ArrayList<KO> res = new ArrayList<>();
        KO.ifPresent(res::add);
        model.addAttribute("KO", res);
        return "/KO/KOEdit";
    }

    @PostMapping("/KO/{id}/edit")
    public String KOEditUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String klassNapr, @RequestParam String dispNaim, @RequestParam String PO, @RequestParam String SP, Model model){
        KO KO = KOInterface.findById(id).orElseThrow();
        KO.setTitle(title);
        KO.setKlassNapr(klassNapr);
        KO.setDispNaim(dispNaim);
        KO.setPO(PO);
        KO.setSP(SP);
        KOInterface.save(KO);
        return "redirect:/KO";
    }

    @PostMapping("/KO/{id}/remove")
    public String KODelete(@PathVariable(value = "id") long id, Model model){
        KO KO = KOInterface.findById(id).orElseThrow();
        KOInterface.delete(KO);
        return "redirect:/KO";
    }
}