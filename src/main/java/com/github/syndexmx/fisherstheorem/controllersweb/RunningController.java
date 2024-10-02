package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RunningController {

    @Autowired
    SimulationService simulationService;

    @Autowired
    SimulationMonitoringService simulationMonitoringService;

    @PostMapping("/running")
    public String runningPageStart(Model model) {
        model.addAttribute("running", simulationMonitoringService.getStatus());
        simulationService.simulate();
        return "running";
    }

    @GetMapping("/running")
    public String runningPage(Model model) {
        model.addAttribute("running", simulationMonitoringService.getStatus());
        return "running";
    }

}
