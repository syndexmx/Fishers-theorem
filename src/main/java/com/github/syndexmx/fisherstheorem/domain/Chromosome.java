package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class Chromosome implements Cloneable {

    @Getter
    List<Gene> genes;

    @Getter
    long fitnessDeviation; // in arbitrary small integer steps

    public Chromosome(Integer numberGenes) {
        List<Gene> generatedGenes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            generatedGenes.add(new Gene(Gene.STARTING_FITNESS_DEVIATION));
        }
        this.genes = generatedGenes;
        this.fitnessDeviation = this.collectFitness();
    }

    private long collectFitness() {
        long joinFitness =
                genes.stream().mapToLong(gene -> gene.getFitnessDeviation())
                        .reduce(0, (accumulator, fitnessDeviation) -> accumulator + fitnessDeviation);
        return joinFitness;
    }

    public void mutate(int mutationEffect) {
        int mutatedGene = MathUtils.getRandom(genes.size());
        genes.get(mutatedGene).mutate(mutationEffect);
        fitnessDeviation = this.collectFitness();
    }

    @Override
    public Chromosome clone() {
        try {
            Chromosome cloneChromo = (Chromosome) super.clone();
            List<Gene> clonedGenes = new ArrayList<>();
            this.genes.stream().forEach(gene -> clonedGenes.add(gene.clone()));
            cloneChromo.genes = clonedGenes;
            cloneChromo.fitnessDeviation = cloneChromo.collectFitness();
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
