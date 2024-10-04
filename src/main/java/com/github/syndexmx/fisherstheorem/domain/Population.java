package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Population {

    @Getter
    @Setter
    List<Individual> males;

    @Getter
    @Setter
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
        // TO DO logging level
        System.out.print("Sex: " + childPopulation.males.size() + " " + childPopulation.females.size());
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
        Population childPopulation = new Population(simulationScheme);
        List<Individual> survivedMales = new ArrayList<>();
        this.males.stream().filter(i -> i.collectFitness() > 0
                        | (MathUtils.getRandomBooleanWith(1.0 + i.collectFitness())))
                .forEach(i -> survivedMales.add(i.clone()));
        this.males = survivedMales;
        List<Individual> survivedFemales = new ArrayList<>();
        this.females.stream().filter(i -> i.collectFitness() > 0
                     | (MathUtils.getRandomBooleanWith(1.0 + i.collectFitness())))
                .forEach(i -> survivedFemales.add(i.clone()));
        this.females = survivedFemales;
        // TO DO logging level
        System.out.print("; Survival: " + this.males.size() + " " + this.females.size());
    }

    public void differentiallyReproduce() {
        List<Individual> updatedMales = new ArrayList<Individual>();
        this.males.stream().forEach(i -> updatedMales.add(i.clone()));
        this.males.stream().filter(i -> (i.collectFitness() > 0
                        & (MathUtils.getRandomBooleanWith(i.collectFitness()))))
                .forEach(i -> updatedMales.add(i.clone()));
        this.males = updatedMales;
        List<Individual> updatedFemales = new ArrayList<Individual>();
        this.females.stream().forEach(i -> updatedFemales.add(i.clone()));
        this.females.stream().filter(i -> (i.collectFitness() > 0
                        & (MathUtils.getRandomBooleanWith(i.collectFitness()))))
                .forEach(i -> updatedFemales.add(i.clone()));
        this.females = updatedFemales;
        // TO DO logging level
        System.out.println(";  Reproduction: " + this.males.size() + ' ' + this.females.size());
    }
}
