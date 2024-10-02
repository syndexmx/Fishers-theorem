package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;

public class Individual {

    Individual(GenomeScheme genomeScheme) {
        this.paternalGenome = new Genome(genomeScheme);
        this.maternalGenome = new Genome(genomeScheme);
    }

    @Getter
    private Genome paternalGenome;

    @Getter
    private Genome maternalGenome;

    public double collectFitness() {
        return MathUtils.collectFitness(paternalGenome.collectFitness(), maternalGenome.collectFitness());
    }

}
