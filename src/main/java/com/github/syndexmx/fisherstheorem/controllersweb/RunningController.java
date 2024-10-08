package com.github.syndexmx.fisherstheorem.controllersweb;

import com.github.syndexmx.fisherstheorem.domain.MutationProfile;
import com.github.syndexmx.fisherstheorem.domain.SimulationScheme;
import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
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
    public String runningPageStart(@RequestParam("populationlimit") Integer populationLimit,
                                   @RequestParam("generationslimit") Integer generationsLimit,
                                   @RequestParam("beneficialrate") Double beneficialRate,
                                   @RequestParam("beneficialeffect") Double beneficialEffect,
                                   @RequestParam("deleteriousrate") Double deleteriousRate,
                                   @RequestParam("deleteriouseffect") Double deleteriousEffect,
                                   Model model) {
        MutationProfile mutationProfile = MutationProfile.builder()
                .beneficialMutationsRate(beneficialRate)
                .beneficialMutationsEffect(beneficialEffect)
                .deleteriousMutationsRate(deleteriousRate)
                .deleteriousMutationsEffect(deleteriousEffect)
                .build();
        SimulationScheme simulationScheme = SimulationScheme.builder()
                .populationLimit(populationLimit)
                .generationsLimit(generationsLimit)
                .mutationProfile(mutationProfile)
                .build();
        Long id = simulationService.simulate(simulationScheme);
        model.addAttribute("simulationid", id);
        model.addAttribute("populationlimit", populationLimit);
        model.addAttribute("generationslimit", generationsLimit);
        model.addAttribute("beneficialrate", beneficialRate);
        model.addAttribute("beneficialeffect", beneficialEffect);
        model.addAttribute("deleteriousrate", deleteriousRate);
        model.addAttribute("deleteriouseffect", deleteriousEffect);
        return "running";
    }
}
