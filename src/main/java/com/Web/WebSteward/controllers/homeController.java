package com.Web.WebSteward.controllers;

import com.Web.WebSteward.models.TOiR;
import com.Web.WebSteward.interfaces.tOiRInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class homeController {

    @Autowired
    private tOiRInterface TOiRInterface;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<TOiR> TOiR = TOiRInterface.findAll();
        model.addAttribute("TOiR", TOiR);
        return "home";
    }
}