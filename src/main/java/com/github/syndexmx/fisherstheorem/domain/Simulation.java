package com.github.syndexmx.fisherstheorem.domain;


public class Simulation {

    private Generation generation;

    private SimulationScheme simulationScheme;

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
