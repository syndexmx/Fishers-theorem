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
            Integer geneNumber = genomeScheme.getScheme().get(index);
            Chromosome chromosome = new Chromosome(geneNumber);
            generatedChromosomes.add(chromosome);
        }
        chromosomes = generatedChromosomes;
    }

    public double collectFitness() {
        double joinFitness =
                chromosomes.stream().mapToDouble(chromosome -> chromosome.collectFitness())
                        .reduce(0.0, (accumulator, fitness) -> accumulator + fitness);
        return joinFitness;
    }

    public void mutate(double mutationEffect) {
        int mutatedChromosome = genomeScheme.getGeneToChromosomeMap().get(
            MathUtils.getRandom(genomeScheme.getGenesOverall()));
        chromosomes.get(mutatedChromosome).mutate(mutationEffect);
    }


    @Override
    public Genome clone() {
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
