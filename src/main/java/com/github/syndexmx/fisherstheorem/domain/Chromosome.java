package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Chromosome {

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

}
