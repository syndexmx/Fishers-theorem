package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Slf4j
public class Genome implements Cloneable {

    @Setter
    @Getter
    private List<Chromosome> chromosomes;

    @Getter
    @Setter
    private GenomeScheme genomeScheme;

    public Genome(GenomeScheme genomeScheme) {
        this.genomeScheme = genomeScheme;
        List<Chromosome> generatedChromosomes = new ArrayList<Chromosome>();
        for (Integer index : genomeScheme.getScheme()) {
            // TO DO Change logging level
            log.warn("Gen chromosome with " + genomeScheme.getScheme().get(index).toString() + " genes");
            Integer geneNumber = genomeScheme.getScheme().get(index);
            Chromosome chromosome = new Chromosome(geneNumber);
            generatedChromosomes.add(chromosome);
            // TO DO
            log.warn("Chr added " + chromosome.toString());
        }
        chromosomes = generatedChromosomes;
    }

    public double collectFitness() {
        double joinFitness =
                chromosomes.stream().map(chromosome -> chromosome.collectFitness())
                        .reduce(1.0, (accumulator, fitness)
                                -> MathUtils.collectFitness(accumulator, fitness));
        return joinFitness;
    }

    public void mutate(double mutationEffect) {
        // TO DO Change logging level
        log.warn("Mutation by " + genomeScheme.toString());
        log.warn("with map " + genomeScheme.getGeneToChromosomeMap().toString());
        log.warn("from " + chromosomes.toString());
        int mutatedChromosome = genomeScheme.getGeneToChromosomeMap().get(
            MathUtils.getRandom(genomeScheme.getGenesOverall()));
        chromosomes.set(mutatedChromosome, chromosomes.get(mutatedChromosome).mutate(mutationEffect));
    }


    @Override
    public Genome clone() {
        // TO DO Change logging level
        log.warn("Genome clonning " + this.toString());
        try {
            Genome clone = (Genome) super.clone();
            List<Chromosome> clonedChromosomes = new ArrayList<Chromosome>();
            this.chromosomes.stream().forEach(chromo -> clonedChromosomes.add(chromo.clone()));
            clone.genomeScheme = this.genomeScheme;
            clone.chromosomes = clonedChromosomes;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
