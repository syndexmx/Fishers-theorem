package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.SimulationConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Generation {

    @Getter
    private int generationIndex;

    @Getter
    private Population population;

    public Generation(int populationSize, GenomeScheme genomeScheme) {
        population = new Population(populationSize, genomeScheme);
        generationIndex = 0;
    }

    public Generation(Population population, int nextGenerationIndex) {
        this.population = population;
        generationIndex = nextGenerationIndex;
    }

    public Generation nextGeneration(Double reproductionFactor) {
        Population childPopulation = population.sex(reproductionFactor);
        Generation childGeneration = new Generation(childPopulation, generationIndex + 1);
        System.out.println(generationIndex);
        return childGeneration;
    }

}
