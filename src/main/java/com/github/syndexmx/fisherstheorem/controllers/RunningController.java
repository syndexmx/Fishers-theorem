package com.github.syndexmx.fisherstheorem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RunningController {

    @PostMapping("/running")
    public String runningPage(Model model) {
        model.addAttribute("running", "");
        return "running";
    }
}
