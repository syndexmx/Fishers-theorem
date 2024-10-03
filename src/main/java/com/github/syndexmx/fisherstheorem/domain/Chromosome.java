package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@NoArgsConstructor
@Slf4j
public class Chromosome implements Cloneable {

    @Getter
    @Setter
    List<Gene> genes;

    public Chromosome(Integer numberGenes) {
        List<Gene> generatedGenes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            generatedGenes.add(new Gene(1.0));
            // TO DO logging level
            log.warn("Gene 1.0");
        }
        this.genes = generatedGenes;
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
            Chromosome cloneChromo = (Chromosome) super.clone();
            List<Gene> clonedGenes = new ArrayList<>();
            this.getGenes().stream().forEach(gene -> clonedGenes.add(gene.clone()));
            cloneChromo.genes = clonedGenes;
            return cloneChromo;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
