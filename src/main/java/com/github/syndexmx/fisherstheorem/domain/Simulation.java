package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class Simulation {

    private Generation generation;

    public Simulation(SimulationConfig simulationConfig, GenomeConfig genomeConfig) {
        GenomeScheme genomeScheme = new GenomeScheme(genomeConfig);
        Generation generation = new Generation(simulationConfig.getPopulationSize(), genomeScheme);
    }

    private SimulationConfig simulationConfig;

    private GenomeConfig genomeConfig;

    public Generation nextGeneration() {
        return generation.nextGeneration(simulationConfig.getReproductionFactor());
    }

}
