package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class Simulation {

    private Generation generation;

    public Simulation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        Generation generation = new Generation(simulationScheme.getPopulationSize(), genomeScheme);
    }

    private SimulationScheme simulationScheme;

    private GenomeScheme genomeScheme;

    public Generation nextGeneration(SimulationScheme simulationScheme) {
        return generation.nextGeneration(simulationScheme.getReproductionFactor());
    }

}
