package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;

public class Simulation {

    @Getter
    private Generation generation;

    @Getter
    private SimulationScheme simulationScheme;

    @Getter
    private GenomeScheme genomeScheme;

    public Simulation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        this.simulationScheme = simulationScheme;
        generation = new Generation(this.simulationScheme, this.genomeScheme);
    }

    public int getGenerationIndex() {
        return generation.getGenerationIndex();
    }

    public void runSimulation() {
        while (generation.getGenerationIndex() < simulationScheme.getGenerationsLimit()) {
            generation = generation.nextGeneration(simulationScheme.getReproductionFactor());
        }
    }
}
