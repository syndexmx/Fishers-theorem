package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;

import java.util.HashSet;
import java.util.Set;

public class Population {

    Set<Individual> males;

    Set<Individual> females;

    Population(int populationSize, GenomeScheme genomeScheme) {
        males = new HashSet<Individual>();
        females = new HashSet<Individual>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(genomeScheme);
            if (MathUtils.getRandom(2)==1) {
                individual.setSex(Individual.Sex.MALE);
                males.add(individual);
            } else {
                individual.setSex(Individual.Sex.FEMALE);
                females.add(individual);
            }
        }
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
