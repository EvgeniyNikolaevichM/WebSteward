package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.spInterface;
import com.Web.WebSteward.models.SP;
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
public class spController {

    @Autowired
    private spInterface SPInterface;

    @GetMapping("/SP")
    public String SP(Model model) {
        Iterable<SP> SP = SPInterface.findAll();
        model.addAttribute("SP", SP);
        return "/SP/SP";
    }

    @GetMapping("/SPAdd")
    public String SPAdd(Model model) {
        return "/SP/SPAdd";
    }

    @PostMapping("/SPAdd")
    public String newSPAdd(@RequestParam String title, @RequestParam String SPinPO, Model model){
        SP SP = new SP(title,SPinPO);
        SPInterface.save(SP);
        return "redirect:/SP";
    }

    @GetMapping("/SP/{id}")
    public String SPDetails(@PathVariable(value = "id") long id, Model model) {
        if(!SPInterface.existsById(id)){
            return "redirect:/SP";
        }
        Optional<SP> SP = SPInterface.findById(id);
        ArrayList<SP> ressp = new ArrayList<>();
        SP.ifPresent(ressp::add);
        model.addAttribute("SP", ressp);
        return "/SP/SPDetails";
    }

    @GetMapping("/SP/{id}/edit")
    public String SPEdit(@PathVariable(value = "id") long id, Model model) {
        if(!SPInterface.existsById(id)){
            return "redirect:/SP";
        }
        Optional<SP> SP = SPInterface.findById(id);
        ArrayList<SP> res = new ArrayList<>();
        SP.ifPresent(res::add);
        model.addAttribute("SP", res);
        return "/SP/SPEdit";
    }

    @PostMapping("/SP/{id}/edit")
    public String SPEditUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String SPinPO, Model model){
        SP SP = SPInterface.findById(id).orElseThrow();
        SP.setTitle(title);
        SP.setSPinPO(SPinPO);
        SPInterface.save(SP);
        return "redirect:/SP";
    }

    @PostMapping("/SP/{id}/remove")
    public String SPDelete(@PathVariable(value = "id") long id, Model model){
        SP SP = SPInterface.findById(id).orElseThrow();
        SPInterface.delete(SP);
        return "redirect:/SP";
    }
}