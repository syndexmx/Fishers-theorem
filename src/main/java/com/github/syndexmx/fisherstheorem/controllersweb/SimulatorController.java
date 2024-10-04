package com.github.syndexmx.fisherstheorem.controllersweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SimulatorController {

    @GetMapping("/simulation")
    public String indexPage(Model model) {
        model.addAttribute("running", "");
        return "simulate";
    }
}
