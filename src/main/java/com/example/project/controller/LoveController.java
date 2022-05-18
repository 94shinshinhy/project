package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoveController {

    @GetMapping("/love")
    public String love(Model model){

        String name = "김유현";
        model.addAttribute("name", name);
        return "love";
    }

    @GetMapping("/love2")
    public String love2(Model model){

        String name = "유현";
        model.addAttribute("name", name);
        return "love2";
    }
}
