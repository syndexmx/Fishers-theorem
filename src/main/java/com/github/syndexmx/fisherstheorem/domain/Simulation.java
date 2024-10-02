package com.github.syndexmx.fisherstheorem.domain;


public class Simulation {

    private Generation generation;

    public Simulation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        this.simulationScheme = simulationScheme;
        generation = new Generation(simulationScheme.getPopulationSize(), this.genomeScheme);
    }

    private SimulationScheme simulationScheme;

    private GenomeScheme genomeScheme;

    public int getGenerationIndex() {
        return generation.getGenerationIndex();
    }

    public void run() {
        while (generation.getGenerationIndex() < simulationScheme.getGenerationsLimit()) {
            generation.nextGeneration(simulationScheme.getReproductionFactor());
        }
    }
}
