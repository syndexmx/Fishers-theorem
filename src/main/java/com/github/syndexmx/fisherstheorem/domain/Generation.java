package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
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
        // TO DO logging level
        log.warn("GENERATION 0. Population seeded");
    }

    public Generation(Population childPopulation, int nextGenerationIndex) {
        this.population = childPopulation;
        generationIndex = nextGenerationIndex;
    }

    public Generation nextGeneration(Double reproductionFactor) {
        generationIndex++;
        // TO DO logging level
        if (generationIndex % 10 == 0)
            System.out.println("Generation " + generationIndex);

        Population childPopulation = population.sex(reproductionFactor);
        childPopulation.differentiallySurvive();
        childPopulation.differentiallyReproduce();

        Generation childGeneration = new Generation(childPopulation, generationIndex);
        Double fitness = childPopulation.collectFitness();

        // TO DO Change logging level
        if (generationIndex % 10 == 0)
            System.out.println(" Fitness: " + fitness);
        return childGeneration;
    }

}
