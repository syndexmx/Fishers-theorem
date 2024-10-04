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
    @Setter
    List<Gene> genes;

    public Chromosome(Integer numberGenes) {
        List<Gene> generatedGenes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            generatedGenes.add(new Gene(Gene.STARTING_FITNESS));
        }
        this.genes = generatedGenes;
    }

    public double collectFitness() {
        double joinFitness =
                genes.stream().mapToDouble(gene -> gene.getFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

    public void mutate(double mutationEffect) {
        int mutatedGene = MathUtils.getRandom(genes.size());
        genes.get(mutatedGene).mutate(mutationEffect);
    }

    @Override
    public Chromosome clone() {
        try {
            Chromosome cloneChromo = (Chromosome) super.clone();
            List<Gene> clonedGenes = new ArrayList<>();
            this.genes.stream().forEach(gene -> clonedGenes.add(gene.clone()));
            cloneChromo.genes = clonedGenes;
            return cloneChromo;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
