package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        Long id = simulationService.simulate();
        model.addAttribute("simulationid", id);
        return "running";
    }

    @GetMapping("/running/{simulationid}")
    public String runningPage(@PathVariable(value = "simulationid") Long simulationId, Model model) {
        model.addAttribute("simulationid", simulationId);
        model.addAttribute("simulationgeneration", simulationMonitoringService.getGenerations(simulationId));
        model.addAttribute("simulationfitness", simulationMonitoringService.getFitness(simulationId));
        model.addAttribute("simulationstartdfdt", simulationMonitoringService.getStartDfDt(simulationId));
        model.addAttribute("simulationenddfdt", simulationMonitoringService.getEndDfDt(simulationId));
        return "running";
    }

}
