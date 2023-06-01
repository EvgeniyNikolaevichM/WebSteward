package com.Web.WebSteward.controllers;

import com.Web.WebSteward.models.Res;
import com.Web.WebSteward.services.ResService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/res")
public class ResController {

    private final ResService resService;

    @Autowired
    public ResController(ResService resService) {
        this.resService = resService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("res", resService.findAll());
        return "res/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", resService.findOne(id));
        model.addAttribute("year", resService.findOne(id));

        return "res/show";
    }


    @GetMapping("/new")
    public String newRes(@ModelAttribute("res") Res res) {
        return "res/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("res") @Valid Res res,
                         BindingResult bindingResult) {
//        resValidator.validate(res, bindingResult);

        if (bindingResult.hasErrors())
            return "res/new";

        resService.save(res);
        return "redirect:/res";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("title", resService.findOne(id));
        return "res/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("res") @Valid Res res, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "res/edit";

        resService.update(id, res);
        return "redirect:/res";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        resService.delete(id);
        return "redirect:/res";
    }
}