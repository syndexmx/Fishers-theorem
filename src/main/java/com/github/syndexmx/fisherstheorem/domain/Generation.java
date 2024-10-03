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
    }

    public Generation(Population population, int nextGenerationIndex) {
        this.population = population;
        generationIndex = nextGenerationIndex;
    }

    public Generation nextGeneration(Double reproductionFactor) {
        Population childPopulation = population.sex(reproductionFactor);

        Generation childGeneration = new Generation(childPopulation,
                generationIndex + 1);
        Double fitness = population.collectFitness();
        // TO DO Change logging level
        log.warn(generationIndex.toString() + ' ' + fitness.toString());
        return childGeneration;
    }

}
