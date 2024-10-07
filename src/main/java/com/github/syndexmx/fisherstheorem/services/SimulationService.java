package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.MutationsConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.*;
import com.github.syndexmx.fisherstheorem.entities.MutationProfileEntity;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationSchemeEntity;
import com.github.syndexmx.fisherstheorem.repositories.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    GenomeConfig genomeConfig;

    private GenomeScheme genomeScheme;

    private MutationProfile mutationProfile;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    ResultsLoggingService resultsLoggingService;

    public Long simulate(SimulationScheme simulationScheme) {
        genomeScheme = new GenomeScheme(
                genomeConfig.getMapChromosomeToGenes(),
                genomeConfig.getGeneNumberReductionFactor());
        mutationProfile = simulationScheme.getMutationProfile();
        Results results = Results.builder()
                .build();
        Simulation simulation = new Simulation(simulationScheme, genomeScheme, results);
        Long id = simulationRepository.save(simulationToSimulationEntity(simulation)).getSimulationId();
        simulation.setResultsLoggingService(resultsLoggingService);
        Thread thread = new Thread(){
            public void run() {
                simulation.runSimulation(id);
            }
        };
        thread.start();
        return id;
    }

    public SimulationEntity simulationToSimulationEntity(Simulation simulation) {
        return SimulationEntity.builder()
                .mutationProfileEntity(mutationProfileToMutationProfileEntity(mutationProfile))
                .simulationSchemeEntity(simulationSchemeToSimulationSchemeEntity(
                        simulation.getSimulationScheme()))
                .resultsEntity(ResultsEntity.builder()
                        .build())
                .build();
    }

    private MutationProfileEntity mutationProfileToMutationProfileEntity(MutationProfile mutationProfile) {
        return MutationProfileEntity.builder()
                .beneficialMutationsRate(mutationProfile.getBeneficialMutationsRate())
                .beneficialMutationsEffect(mutationProfile.getBeneficialMutationsEffect())
                .deleteriousMutationsRate(mutationProfile.getDeleteriousMutationsRate())
                .deleteriousMutationsEffect(mutationProfile.getDeleteriousMutationsEffect())
                .build();
    }

    private SimulationSchemeEntity simulationSchemeToSimulationSchemeEntity(SimulationScheme simulationScheme) {
        return SimulationSchemeEntity.builder()
                .generationsLimit(simulationScheme.getGenerationsLimit())
                .populationLimit(simulationScheme.getPopulationLimit())
                .build();
    }

}
