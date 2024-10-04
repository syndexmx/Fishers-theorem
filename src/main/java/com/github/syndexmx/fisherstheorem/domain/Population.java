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
    }

    public Population sex(Double reproductionFactor) {
        Population childPopulation = new Population(simulationScheme);
        long childPopulationSize = simulationScheme.getPopulationLimit();
        for (int i = 0; i < childPopulationSize; i++) {
            int fatherIndex = MathUtils.getRandom(males.size());
            int motherIndex = MathUtils.getRandom(females.size());
            Individual father = this.males.get(fatherIndex);
            Individual mother = this.females.get(motherIndex);
            Individual child = father.makeChild(mother);
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
        double jointFitness = males.stream().mapToDouble(individ -> individ.collectFitness())
                        .reduce(0.0, (accumulator, fitness) -> accumulator + fitness);
        jointFitness += females.stream().mapToDouble(individ -> individ.collectFitness())
                        .reduce(0.0, (accumulator, fitness) -> accumulator + fitness);
        return jointFitness / this.getSize();
    }

    public Integer getSize() {
        return males.size()+ females.size();
    }

    public void differentiallySurvive() {
        List<Individual> survivedMales = new ArrayList<>();
        males.stream().filter(i -> (i.collectFitness() < 0
                        & (MathUtils.getRandomBooleanWith(1 + i.collectFitness()))))
                .forEach(i -> survivedMales.add(i.clone()));
        males = survivedMales;
        List<Individual> survivedFemales = new ArrayList<>();
        females.stream().filter(i -> (i.collectFitness() < 0
                        & (MathUtils.getRandomBooleanWith(1 + i.collectFitness()))))
                .forEach(i -> survivedFemales.add(i.clone()));
        females = survivedFemales;
    }

    public void differentiallyReproduce() {
        List<Individual> additionalMales = males.stream().filter(i -> (i.collectFitness() > 0
                        & (MathUtils.getRandomBooleanWith(i.collectFitness()))))
                .toList();
        additionalMales.stream().forEach(i -> males.add(i.clone()));
        List<Individual> additionalFemales = females.stream().filter(i -> (i.collectFitness() > 0
                        & (MathUtils.getRandomBooleanWith(i.collectFitness()))))
                .toList();
        additionalFemales.stream().forEach(i -> females.add(i.clone()));
    }
}
