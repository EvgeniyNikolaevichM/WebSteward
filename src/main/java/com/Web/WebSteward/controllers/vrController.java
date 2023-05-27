package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.vrInterface;
import com.Web.WebSteward.models.VR;
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
public class vrController {

    @Autowired
    private vrInterface VRInterface;

    @GetMapping("/VR")
    public String VR(Model model) {
        Iterable<VR> VR = VRInterface.findAll();
        model.addAttribute("VR", VR);
        return "/VR/VR";
    }

    @GetMapping("/VRAdd")
    public String VRAdd(Model model) {
        return "/VR/VRAdd";
    }

    @PostMapping("/VRAdd")
    public String newVRAdd(@RequestParam String title, Model model){
        VR VR = new VR(title);
        VRInterface.save(VR);
        return "redirect:/VR";
    }

    @GetMapping("/VR/{id}")
    public String VRDetails(@PathVariable(value = "id") long id, Model model) {
        if(!VRInterface.existsById(id)){
            return "redirect:/VR";
        }
        Optional<VR> VR = VRInterface.findById(id);
        ArrayList<VR> res = new ArrayList<>();
        VR.ifPresent(res::add);
        model.addAttribute("VR", res);
        return "/VR/VRDetails";
    }

    @GetMapping("/VR/{id}/edit")
    public String VREdit(@PathVariable(value = "id") long id, Model model) {
        if(!VRInterface.existsById(id)){
            return "redirect:/VR";
        }
        Optional<VR> VR = VRInterface.findById(id);
        ArrayList<VR> res = new ArrayList<>();
        VR.ifPresent(res::add);
        model.addAttribute("VR", res);
        return "/VR/VREdit";
    }

    @PostMapping("/VR/{id}/edit")
    public String VREditUpdate(@PathVariable(value = "id") long id, @RequestParam String title, Model model){
        VR VR = VRInterface.findById(id).orElseThrow();
        VR.setTitle(title);
        VRInterface.save(VR);
        return "redirect:/VR";
    }

    @PostMapping("/VR/{id}/remove")
    public String VRDelete(@PathVariable(value = "id") long id, Model model){
        VR VR = VRInterface.findById(id).orElseThrow();
        VRInterface.delete(VR);
        return "redirect:/VR";
    }
}