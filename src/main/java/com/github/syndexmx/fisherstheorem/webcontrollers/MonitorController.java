package com.github.syndexmx.fisherstheorem.webcontrollers;

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
        double beneficialStream = simulationMonitoringService.getBeneficialMutationRate(simulationId)
                * simulationMonitoringService.getBeneficialMutationEffect(simulationId);
        model.addAttribute("beneficialstream",beneficialStream);
        if (beneficialStream > 0 & dfdt > 0) {
            model.addAttribute("beneficialfixationperc", dfdt / beneficialStream);
        } else {
            model.addAttribute("beneficialfixationperc", 0.0);
        }
        double deleteriousStream = - simulationMonitoringService.getDeleteriousMutationRate(simulationId)
                * simulationMonitoringService.getDeleteriousMutationEffect(simulationId);
        model.addAttribute("deleteriousstream", deleteriousStream);
        if (deleteriousStream < 0 & dfdt < 0) {
            model.addAttribute("deleteriousfixationperc",dfdt / beneficialStream);
        } else {
            model.addAttribute("deleteriousfixationperc", 0.0);
        }
        return "monitor";
    }
}
