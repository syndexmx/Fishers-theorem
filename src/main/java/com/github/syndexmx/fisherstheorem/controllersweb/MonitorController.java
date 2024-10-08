package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MonitorController {

    @Autowired
    SimulationService simulationService;

    @Autowired
    SimulationMonitoringService simulationMonitoringService;

    @GetMapping("/monitor/{simulationid}")
    public String runningPage(@PathVariable(value = "simulationid") Long simulationId, Model model) {
        model.addAttribute("simulationid", simulationId);
        model.addAttribute("simulationgeneration", simulationMonitoringService.getGenerations(simulationId));
        model.addAttribute("simulationfitness", simulationMonitoringService.getFitness(simulationId));
        model.addAttribute("simulationstartdfdt", simulationMonitoringService.getStartDfDt(simulationId));
        double dfdt = simulationMonitoringService.getEndDfDt(simulationId);
        model.addAttribute("simulationenddfdt", dfdt);
        model.addAttribute("beneficialrate",simulationMonitoringService.getBeneficialMutationRate(simulationId));
        model.addAttribute("beneficialeffect",simulationMonitoringService.getBeneficialMutationEffect(simulationId));
        model.addAttribute("deleteriousrate",simulationMonitoringService.getDeleteriousMutationRate(simulationId));
        model.addAttribute("deleteriouseffect",simulationMonitoringService.getDeleteriousMutationEffect(simulationId));
        return "monitor";
    }
}
