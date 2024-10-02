package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Genome implements Cloneable {

    @Setter
    @Getter
    private List<Chromosome> chromosomes;

    @Getter
    private GenomeScheme genomeScheme;

    public Genome(GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        chromosomes = new ArrayList<Chromosome>();
        for (Integer index : genomeScheme.getScheme()) {
            Chromosome chromosome = new Chromosome(genomeScheme.getScheme().get(index));
        }
    }

    public double collectFitness() {
        double joinFitness =
                chromosomes.stream().map(chromosome -> chromosome.collectFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

    public Genome mutate(double mutationEffect) {
        Genome newGenome = this.cloneGenome();
        int mutatedChromosome = genomeScheme.getGeneToChromosomeMap().get(
                MathUtils.getRandom(genomeScheme.getGenesOverall()));
        chromosomes.set(mutatedChromosome, chromosomes.get(mutatedChromosome).mutate(mutationEffect));
        return newGenome;
    }

    private Genome cloneGenome() {
        Genome newGenome = new Genome(this.genomeScheme);
        List<Chromosome> newChromosomes = chromosomes.stream().toList();
        newGenome.setChromosomes(newChromosomes);
        return newGenome;
    }
}
