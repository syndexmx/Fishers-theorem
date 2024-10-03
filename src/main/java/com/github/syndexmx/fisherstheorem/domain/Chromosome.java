package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@NoArgsConstructor
public class Chromosome implements Cloneable {

    @Getter
    @Setter
    List<Gene> genes;

    public Chromosome(Integer numberGenes) {
        List<Gene> genes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            genes.add(new Gene(1.0));
        }
    }

    public double collectFitness() {
        double joinFitness =
                genes.stream().map(gene -> gene.getFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

    public Chromosome mutate(double mutationEffect) {
        Chromosome newChromosome = new Chromosome();
        List<Gene> newGenes = genes.stream().toList();
        int mutatedGeneIndex = MathUtils.getRandom(genes.size());
        newGenes.get(mutatedGeneIndex).mutate(mutationEffect);
        newChromosome.setGenes(newGenes);
        return null;
    }

    @Override
    public Chromosome clone() {
        try {
            Chromosome clone = (Chromosome) super.clone();
            List<Gene> clonedGenes = new ArrayList<>();
            this.getGenes().stream().forEach(gene -> clonedGenes.add(gene.clone()));
            clone.setGenes(clonedGenes);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
