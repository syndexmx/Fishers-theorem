package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class Chromosome implements Cloneable {

    @Getter
    List<Gene> genes;

    @Getter
    double fitness;

    public Chromosome(Integer numberGenes) {
        List<Gene> generatedGenes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            generatedGenes.add(new Gene(Gene.STARTING_FITNESS));
        }
        this.genes = generatedGenes;
        this.fitness = this.collectFitness();
    }

    private double collectFitness() {
        double joinFitness =
                genes.stream().mapToDouble(gene -> gene.getFitness())
                        .reduce(0.0, (accumulator, fitness) -> accumulator + fitness);
        return joinFitness;
    }

    public void mutate(double mutationEffect) {
        int mutatedGene = MathUtils.getRandom(genes.size());
        genes.get(mutatedGene).mutate(mutationEffect);
        fitness = this.collectFitness();
    }

    @Override
    public Chromosome clone() {
        try {
            Chromosome cloneChromo = (Chromosome) super.clone();
            List<Gene> clonedGenes = new ArrayList<>();
            this.genes.stream().forEach(gene -> clonedGenes.add(gene.clone()));
            cloneChromo.genes = clonedGenes;
            cloneChromo.fitness = cloneChromo.collectFitness();
            return cloneChromo;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void splice(Chromosome anotherChromosome) {
        int chromosomeLength = this.genes.size();
        int splicingPoint = MathUtils.getRandom(chromosomeLength);
        int currentPoint = splicingPoint;
        while (currentPoint < chromosomeLength) {
            // Swapping
            Gene swapGene = this.genes.get(currentPoint);
            this.genes.set(currentPoint, anotherChromosome.genes.get(currentPoint));
            anotherChromosome.genes.set(currentPoint, swapGene);
            currentPoint++;
        }
    }
}
