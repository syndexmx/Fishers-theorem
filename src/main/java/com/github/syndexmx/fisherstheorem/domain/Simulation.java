package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Simulation {

    @Getter
    private Generation generation;

    @Getter
    private SimulationScheme simulationScheme;

    @Getter
    private GenomeScheme genomeScheme;

    @Getter
    private List<Double> fitnessDeviationHistory;

    public Simulation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        this.simulationScheme = simulationScheme;
        generation = new Generation(this.simulationScheme, this.genomeScheme);
        fitnessDeviationHistory = new ArrayList<Double>();
        fitnessDeviationHistory.add(generation.getFitnessDeviation());
        log.info("Generation " + generation.getGenerationIndex());
    }

    public int getGenerationIndex() {
        return generation.getGenerationIndex();
    }

    public void runSimulation() {
        while (generation.getGenerationIndex() < simulationScheme.getGenerationsLimit()) {
            generation = generation.nextGeneration(simulationScheme.getReproductionFactor());
            fitnessDeviationHistory.add(generation.getFitnessDeviation());
            logIt();
        }
    }

    private void logIt() {
        int generationIndex = generation.getGenerationIndex();
        if (generation.getGenerationIndex() > 3) {
            double startPeriodFitnessDev = fitnessDeviationHistory.get(0);
            double middlePeriodFitnessDev = fitnessDeviationHistory.get(generationIndex / 2);
            double endPeriodFitnessDev = fitnessDeviationHistory.get(generationIndex);
            double firstHalfRate = (middlePeriodFitnessDev - startPeriodFitnessDev)
                    / (generationIndex / 2);
            double secondHalfRate = (endPeriodFitnessDev - middlePeriodFitnessDev)
                    / (generationIndex - generationIndex / 2);
            log.info("Generation " + generation.getGenerationIndex()
                    + " fitness = "
                    + (1.0 + generation.getFitnessDeviation())
                    + "  Long-tern fitness change: 1st half df/dt= " + firstHalfRate
                    + "; 2nd half df/dt= " + secondHalfRate);
        }
    }
}
