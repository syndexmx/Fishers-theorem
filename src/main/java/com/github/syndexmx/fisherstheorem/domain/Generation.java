package com.github.syndexmx.fisherstheorem.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Generation {

    private Population population;

    public Generation(int populationSize, GenomeScheme genomeScheme) {

    }

    public Generation(Population population) {
        this.population = population;
    }

    public Generation nextGeneration(Double reproductionFactor) {
        Population childPopulation = population.sex(reproductionFactor);
        Generation childGeneration = new Generation(childPopulation);
        System.out.println("Generation step-over");
        return childGeneration;
    }



}
