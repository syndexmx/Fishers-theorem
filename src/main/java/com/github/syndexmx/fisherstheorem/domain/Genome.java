package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Genome {

    List<Chromosome> chromosomes;

    public Genome(GenomeScheme genomeScheme) {
        chromosomes = new ArrayList<Chromosome>();
        for (Integer index : genomeScheme.getScheme()) {
            Chromosome chromosome = new Chromosome(genomeScheme.getScheme().get(index));

        }
    }

    Genome(Genome prototypeGenome) {

    }

    public double collectFitness() {
        double joinFitness =
                chromosomes.stream().map(chromosome -> chromosome.collectFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

    public void mutate(double mutationEffect) {
        // TO DO Mutation
    }
}
