package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.homeInterface;
import com.Web.WebSteward.models.home;
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
public class homeController {

    @Autowired
    private homeInterface HomeInterface;


    @GetMapping("/")
    public String home(Model model) {
        Iterable<home> home = HomeInterface.findAll();
        model.addAttribute("home", home);
        return "home";
    }

    @GetMapping("/systemAdd")
    public String systemAdd(Model model) {
        return "systemAdd";
    }

    @PostMapping("/systemAdd")
    public String newsystemAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        home home = new home(title, anons, full_text);
        HomeInterface.save(home);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String systemDetails(@PathVariable(value = "id") long id, Model model) {
        if(!HomeInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<home> home = HomeInterface.findById(id);
        ArrayList<home> res = new ArrayList<>();
        home.ifPresent(res::add);
        model.addAttribute("home", res);
        return "systemDetails";
    }

    @GetMapping("/{id}/edit")
    public String systemEdit(@PathVariable(value = "id") long id, Model model) {
        if(!HomeInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<home> home = HomeInterface.findById(id);
        ArrayList<home> res = new ArrayList<>();
        home.ifPresent(res::add);
        model.addAttribute("home", res);
        return "systemEdit";
    }

    @PostMapping("/{id}/edit")
    public String systemEditUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        home home = HomeInterface.findById(id).orElseThrow();
        home.setTitle(title);
        home.setAnons(anons);
        home.setFull_text(full_text);
        HomeInterface.save(home);
        return "redirect:/";
    }

    @PostMapping("/{id}/remove")
    public String systemDelete(@PathVariable(value = "id") long id, Model model){
        home home = HomeInterface.findById(id).orElseThrow();
        HomeInterface.delete(home);
        return "redirect:/";
    }
}