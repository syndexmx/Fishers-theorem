package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@Slf4j
public class RunningController {

    @Autowired
    SimulationService simulationService;

    @Autowired
    SimulationMonitoringService simulationMonitoringService;

    @PostMapping("/running")
    public String runningPageStart(@RequestParam("populationlimit") Integer populationLimit, Model model) {
        Long id = simulationService.simulate();
        model.addAttribute("simulationid", id);
        log.info("Population limit. Requestered: " + populationLimit);
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
