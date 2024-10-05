package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.MutationsConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.GenomeScheme;
import com.github.syndexmx.fisherstheorem.domain.MutationProfile;
import com.github.syndexmx.fisherstheorem.domain.Simulation;
import com.github.syndexmx.fisherstheorem.domain.SimulationScheme;
import com.github.syndexmx.fisherstheorem.entities.MutationProfileEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationSchemeEntity;
import com.github.syndexmx.fisherstheorem.repositories.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    GenomeConfig genomeConfig;

    @Autowired
    SimulationConfig simulationConfig;

    @Autowired
    MutationsConfig mutationsConfig;

    private GenomeScheme genomeScheme;

    private SimulationScheme simulationScheme;

    private MutationProfile mutationProfile;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    ResultsLoggingService resultsLoggingService;

    public Long simulate() {
        genomeScheme = new GenomeScheme(
                genomeConfig.getMapChromosomeToGenes(),
                genomeConfig.getGeneNumberReductionFactor());
        mutationProfile = new MutationProfile(
                mutationsConfig.getBeneficialMutationsRate(),
                mutationsConfig.getBeneficialMutationsEffect(),
                mutationsConfig.getDeleteriousMutationsRate(),
                mutationsConfig.getDeleteriousMutationsEffect());
        simulationScheme = new SimulationScheme(
                simulationConfig.getPopulationSize(),
                simulationConfig.getPopulationLimit(),
                simulationConfig.getReproductionFactor(),
                simulationConfig.getGenerationsLimit(),
                mutationProfile);
        Simulation simulation = new Simulation(simulationScheme, genomeScheme);
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
                .resultsEntity(null)
                .build();
    }

    private MutationProfileEntity mutationProfileToMutationProfileEntity(MutationProfile mutationProfile) {
        return MutationProfileEntity.builder()
                .beneficialMutationsEffect(mutationProfile.getBeneficialMutationsEffect())
                .beneficialMutationsRate(mutationProfile.getBeneficialMutationsEffect())
                .deleteriousMutationsEffect(mutationProfile.getDeleteriousMutationsEffect())
                .deleteriousMutationsRate(mutationProfile.getDeleteriousMutationsEffect())
                .build();
    }

    private SimulationSchemeEntity simulationSchemeToSimulationSchemeEntity(SimulationScheme simulationScheme) {
        return SimulationSchemeEntity.builder()
                .generationsLimit(simulationScheme.getGenerationsLimit())
                .reproductionFactor(simulationScheme.getReproductionFactor())
                .populationSize(simulationScheme.getPopulationSize())
                .populationLimit(simulationScheme.getPopulationLimit())
                .build();
    }

}
