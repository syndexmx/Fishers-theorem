package com.github.syndexmx.fisherstheorem.webcontrollers;

import com.github.syndexmx.fisherstheorem.configurations.MutationsConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SimulatorController {

    @Autowired
    SimulationConfig simulationConfig;

    @Autowired
    MutationsConfig mutationsConfig;

    @GetMapping("/simulation")
    public String indexPage(Model model) {
        model.addAttribute("running", "");
        model.addAttribute("populationlimit", simulationConfig.getPopulationLimit());
        model.addAttribute("generationslimit", simulationConfig.getGenerationsLimit());
        model.addAttribute("beneficialrate", mutationsConfig.getBeneficialMutationsRate());
        model.addAttribute("beneficialeffect", mutationsConfig.getBeneficialMutationsEffect());
        model.addAttribute("deleteriousrate", mutationsConfig.getDeleteriousMutationsRate());
        model.addAttribute("deleteriouseffect", mutationsConfig.getDeleteriousMutationsEffect());
        return "simulation";
    }
}
