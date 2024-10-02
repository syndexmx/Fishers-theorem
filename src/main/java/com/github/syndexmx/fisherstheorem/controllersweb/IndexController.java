package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    SimulationService simulationService;

    @GetMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("running", "");
        simulationService.simulate();
        return "index";
    }
}
