package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Population {

    @Getter
    List<Individual> males;

    @Getter
    List<Individual> females;

    Population() {
        this.males = new ArrayList<Individual>();
        this.females = new ArrayList<Individual>();
    }

    Population(Population population) {
        this.males = population.getMales();
        this.females = population.getFemales();
    }

    Population(List<Individual> males, List<Individual> females) {
        this.males = males;
        this.females = females;
    }

    Population(int populationSize, GenomeScheme genomeScheme) {
        males = new ArrayList<Individual>();
        females = new ArrayList<Individual>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(genomeScheme);
            if (MathUtils.getRandom(2) == 1) {
                individual.setSex(Individual.Sex.MALE);
                males.add(individual);
            } else {
                individual.setSex(Individual.Sex.FEMALE);
                females.add(individual);
            }
        }
    }

    public Population sex(Double reproductionFactor) {
        Population childPopulation = new Population();

        // TODO : Make Sex

        return childPopulation;
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
