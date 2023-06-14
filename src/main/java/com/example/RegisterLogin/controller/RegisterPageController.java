package com.example.RegisterLogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterPageController {

    @GetMapping("/register_page")
    public String registerPage(Model model) {
        model.addAttribute("message", "Yo Boi");
        return "registerPage";
    }
}
