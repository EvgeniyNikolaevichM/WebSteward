package com.Web.WebSteward.controllers;

import com.Web.WebSteward.interfaces.resInterface;
import com.Web.WebSteward.models.Res;
import com.Web.WebSteward.services.ResService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/res")
public class ResController {

    private final ResService resService;
    @Autowired
    public ResController(ResService resService) {
        this.resService = resService;
    }
    @Autowired
    private resInterface resInterface;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("res", resService.findAll());
        return "res/index";
    }

    @GetMapping("/{id}")
    public String ResDetails(@PathVariable(value = "id") int id, Model model) {
        if(!resInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<Res> Res = resInterface.findById(id);
        ArrayList<Res> ressp = new ArrayList<>();
        Res.ifPresent(ressp::add);
        model.addAttribute("res", ressp);
        return "res/show";
    }

    @GetMapping("/new")
    public String newRes(@ModelAttribute("res") Res res) {
        return "res/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("res") @Valid Res res,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "res/new";
        resService.save(res);
        return "redirect:/res";
    }

    @GetMapping("/{id}/edit")
    public String ResEdit(@PathVariable(value = "id") int id, Model model) {
        if(!resInterface.existsById(id)){
            return "redirect:/";
        }
        Optional<Res> Res = resInterface.findById(id);
        ArrayList<Res> res = new ArrayList<>();
        Res.ifPresent(res::add);
        model.addAttribute("res", res);
        return "res/edit";
    }

    @PostMapping("/{id}/edit")
    public String ResEditUpdate(@PathVariable(value = "id") int id, @RequestParam String title,
                                @RequestParam String year, Model model,@ModelAttribute("res") @Valid Res res, BindingResult bindingResult){
        Res Res = resInterface.findById(id).orElseThrow();
        Res.setTitle(title);
        Res.setYear(year);
        if (bindingResult.hasErrors())
            return "res/edit";
        resInterface.save(Res);
        return "redirect:/res";
    }

    @PostMapping("/{id}/remove")
    public String ResDelete(@PathVariable(value = "id") int id, Model model){
        Res Res = resInterface.findById(id).orElseThrow();
        resInterface.delete(Res);
        return "redirect:/res";
    }
}