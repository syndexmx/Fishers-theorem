package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.GenomeScheme;
import com.github.syndexmx.fisherstheorem.domain.Simulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    GenomeConfig genomeConfig;

    @Autowired
    SimulationConfig simulationConfig;

    private GenomeScheme genomeScheme;

    public void simulate() {
        int geneCount = 0;
        genomeScheme = new GenomeScheme(genomeConfig);
        for (Integer chromosomeNumber : genomeScheme.getScheme().keySet()) {
            geneCount += genomeScheme.getScheme().get(chromosomeNumber);
        }
        Simulation simulation = new Simulation(simulationConfig, genomeConfig);
        for (int genNumber = 0; genNumber < simulationConfig.getGenerationsLimit(); genNumber++) {
            simulation.nextGeneration();
        }
    }

}
