package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import com.github.syndexmx.fisherstheorem.domain.GenomeScheme;
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

    private GenomeScheme genomeScheme;

    private SimulationScheme simulationScheme;

    public void simulate() {
        genomeScheme = new GenomeScheme(genomeConfig.getMapChromosomeToGenes(),
                genomeConfig.getGeneNumberReductionFactor());
        simulationScheme = new SimulationScheme(simulationConfig.getPopulationSize(),
                simulationConfig.getPopulationLimit(),
                simulationConfig.getReproductionFactor(),
                simulationConfig.getGenerationsLimit());
        Simulation simulation = new Simulation(simulationScheme, genomeScheme);
        for (int genNumber = 0; genNumber < simulationConfig.getGenerationsLimit(); genNumber++) {
            simulation.nextGeneration(simulationScheme);
        }
    }

}
