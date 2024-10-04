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

    @Getter
    private double fitnessDeviation;

    public Generation(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        population = new Population(simulationScheme, genomeScheme);
        generationIndex = 0;
        fitnessDeviation = population.getFitnessDeviation();
    }

    public Generation(Population childPopulation, int nextGenerationIndex) {
        this.population = childPopulation;
        generationIndex = nextGenerationIndex;
        fitnessDeviation = population.getFitnessDeviation();
    }

    public Generation nextGeneration(double reproductionFactor) {
        generationIndex++;
        Population childPopulation = population.sex(reproductionFactor);
        childPopulation.differentiallySurvive();
        childPopulation.differentiallyReproduce();
        Generation childGeneration = new Generation(childPopulation, generationIndex);
        return childGeneration;
    }

}
