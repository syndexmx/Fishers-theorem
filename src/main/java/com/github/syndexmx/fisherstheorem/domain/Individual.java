package com.github.syndexmx.fisherstheorem.domain;

import lombok.Setter;

public class Individual {

    Individual(GenomeScheme genomeScheme) {
        this.genome = new Genome(genomeScheme);
    }

    Genome genome;

    enum Sex {
        MALE,
        FEMALE
    }

    @Setter
    Sex sex;

    public double getFitness() {
        return genome.collectFitness();
    }

}
