package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class Generation {

    @Getter
    private Integer generationIndex;

    @Getter
    private Population population;

    public Generation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        population = new Population(simulationScheme, genomeScheme);
        generationIndex = 0;
        log.info("GENERATION 0. Population seeded");
    }

    public Generation(Population childPopulation, int nextGenerationIndex) {
        this.population = childPopulation;
        generationIndex = nextGenerationIndex;
    }

    public Generation nextGeneration(double reproductionFactor) {
        generationIndex++;
        log.info("Generation " + generationIndex + ":  ");
        Population childPopulation = population.sex(reproductionFactor);
        childPopulation.differentiallySurvive();
        childPopulation.differentiallyReproduce();
        Generation childGeneration = new Generation(childPopulation, generationIndex);
        double fitness = childPopulation.collectFitness();
        log.info("Fitness: " + (fitness + 1.0));
        return childGeneration;
    }

}
