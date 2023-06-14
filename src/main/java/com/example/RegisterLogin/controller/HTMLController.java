package com.example.RegisterLogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {

    @GetMapping("/html")
    public String getHTML(Model model) {
        // Add any necessary data to the model
        model.addAttribute("message", "Hello, Thymeleaf!");

        // Return the template name (HTML file without the extension)
        return "registerPage";
    }
}
