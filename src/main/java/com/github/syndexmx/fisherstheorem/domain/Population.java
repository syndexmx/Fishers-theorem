package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Population {

    @Getter
    List<Individual> males;

    @Getter
    List<Individual> females;

    @Getter
    SimulationScheme simulationScheme;

    Population(SimulationScheme simulationScheme, GenomeScheme genomeScheme) {
        males = new ArrayList<Individual>();
        females = new ArrayList<Individual>();
        this.simulationScheme = simulationScheme;
        for (int i = 0; i < simulationScheme.getPopulationSize(); i++) {
            Individual individual = new Individual(genomeScheme, simulationScheme);
            if (MathUtils.getRandom(2) == 1) {
                males.add(individual);
            } else {
                females.add(individual);
            }
        }
        // TO DO logging level
        Integer populationSize = males.size() + females.size();
        log.warn("Population size: " + populationSize.toString());
    }

    public Population sex(Double reproductionFactor) {
        Population childPopulation = new Population(simulationScheme);
        long childPopulationSize = Math.round((males.size() + females.size()) * reproductionFactor);
        for (int i = 0; i < childPopulationSize; i++) {
            int fatherIndex = MathUtils.getRandom(males.size());
            int motherIndex = MathUtils.getRandom(females.size());
            Individual father = this.males.get(fatherIndex);
            Individual child = father.makeChild(females.get(motherIndex));
            if (MathUtils.getRandom(2) == 1) {
                childPopulation.males.add(child);
            } else {
                childPopulation.females.add(child);
            }
        }
        return childPopulation;
    }

    Population(SimulationScheme simulationScheme) {
        this.males = new ArrayList<Individual>();
        this.females = new ArrayList<Individual>();
        this.simulationScheme = simulationScheme;
    }

    public double collectFitness() {
        double joinFitness =
                males.stream().map(individ -> individ.collectFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        joinFitness =
                females.stream().map(individ -> individ.collectFitness())
                        .reduce(joinFitness, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

}
