package com.github.syndexmx.fisherstheorem.controllersapi;

import com.github.syndexmx.fisherstheorem.configurations.MutationsConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.MutationProfile;
import com.github.syndexmx.fisherstheorem.domain.SimulationScheme;
import com.github.syndexmx.fisherstheorem.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping
public class SimulationApiController {
    @Autowired
    SimulationConfig simulationConfig;

    @Autowired
    MutationsConfig mutationsConfig;

    @Autowired
    SimulationService simulationService;

    @GetMapping("/api/simulation")
    public ResponseEntity<SimulationScheme> getSimulationScheme() {
        final SimulationScheme simulationScheme = new SimulationScheme(
                simulationConfig.getPopulationLimit(),
                simulationConfig.getGenerationsLimit(),
                MutationProfile.builder()
                        .beneficialMutationsRate(mutationsConfig.getBeneficialMutationsRate())
                        .beneficialMutationsEffect(mutationsConfig.getBeneficialMutationsEffect())
                        .deleteriousMutationsRate(mutationsConfig.getDeleteriousMutationsRate())
                        .deleteriousMutationsEffect(mutationsConfig.getDeleteriousMutationsEffect())
                        .build()
        );
        return new ResponseEntity(simulationScheme, HttpStatus.OK);
    }

    @PostMapping("/api/simulation")
    public ResponseEntity<Long> createUpdateStar(@RequestBody final SimulationScheme simulationScheme) {
        Long id = simulationService.simulate(simulationScheme);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

}
