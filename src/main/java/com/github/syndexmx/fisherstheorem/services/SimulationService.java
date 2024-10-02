package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.MutationsConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.GenomeScheme;
import com.github.syndexmx.fisherstheorem.domain.MutationProfile;
import com.github.syndexmx.fisherstheorem.domain.Simulation;
import com.github.syndexmx.fisherstheorem.domain.SimulationScheme;
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

    public void simulate() {
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
        Thread thread = new Thread(){
            public void run() {
                simulation.runSimulation();
            }
        };
        thread.start();
    }

}
