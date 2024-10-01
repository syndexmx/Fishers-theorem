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
            Individual individual = new Individual();
            if (MathUtils.getRandom(2)==1) {
                individual.setSex(Individual.Sex.MALE);
                males.add(individual);
            } else {
                individual.setSex(Individual.Sex.FEMALE);
                females.add(individual);
            }
        }
    }

}
