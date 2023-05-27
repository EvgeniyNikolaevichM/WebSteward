package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.poInterface;
import com.Web.WebSteward.models.PO;
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
public class poController {

    @Autowired
    private poInterface POInterface;

    @GetMapping("/PO")
    public String PO(Model model) {
        Iterable<PO> PO = POInterface.findAll();
        model.addAttribute("PO", PO);
        return "/PO/PO";
    }

    @GetMapping("/POAdd")
    public String POAdd(Model model) {
        return "/PO/POAdd";
    }

    @PostMapping("/POAdd")
    public String newPOAdd(@RequestParam String title, Model model){
        PO PO = new PO(title);
        POInterface.save(PO);
        return "redirect:/PO";
    }

    @GetMapping("/PO/{id}")
    public String PODetails(@PathVariable(value = "id") long id, Model model) {
        if(!POInterface.existsById(id)){
            return "redirect:/PO";
        }
        Optional<PO> PO = POInterface.findById(id);
        ArrayList<PO> res = new ArrayList<>();
        PO.ifPresent(res::add);
        model.addAttribute("PO", res);
        return "/PO/PODetails";
    }

    @GetMapping("/PO/{id}/edit")
    public String POEdit(@PathVariable(value = "id") long id, Model model) {
        if(!POInterface.existsById(id)){
            return "redirect:/PO";
        }
        Optional<PO> PO = POInterface.findById(id);
        ArrayList<PO> res = new ArrayList<>();
        PO.ifPresent(res::add);
        model.addAttribute("PO", res);
        return "/PO/POEdit";
    }

    @PostMapping("/PO/{id}/edit")
    public String POEditUpdate(@PathVariable(value = "id") long id, @RequestParam String title, Model model){
        PO PO = POInterface.findById(id).orElseThrow();
        PO.setTitle(title);
        POInterface.save(PO);
        return "redirect:/PO";
    }

    @PostMapping("/PO/{id}/remove")
    public String PODelete(@PathVariable(value = "id") long id, Model model){
        PO PO = POInterface.findById(id).orElseThrow();
        POInterface.delete(PO);
        return "redirect:/PO";
    }
}