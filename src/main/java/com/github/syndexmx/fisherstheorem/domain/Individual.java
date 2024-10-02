package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;

public class Individual {

    private GenomeScheme genomeScheme;

    private MutationProfile mutationProfile;

    Individual(GenomeScheme genomeScheme, SimulationScheme simulationScheme) {
        this.paternalGenome = new Genome(genomeScheme);
        this.maternalGenome = new Genome(genomeScheme);
        this.genomeScheme = genomeScheme;
        this.mutationProfile = simulationScheme.getMutationProfile();
    }

    @Getter
    private Genome paternalGenome;

    @Getter
    private Genome maternalGenome;

    public Individual(Individual father, Individual mother, MutationProfile mutationProfile) {
        Genome fromFather = father.getHaploGenome();
        Genome fromMother = mother.getHaploGenome();
        // TO DO splicing
        paternalGenome = fromFather;
        maternalGenome = fromMother;
        this.mutationProfile = mutationProfile;
        // TO DO mutation
    }

    public double collectFitness() {
        return MathUtils.collectFitness(paternalGenome.collectFitness(), maternalGenome.collectFitness());
    }

    public Genome getHaploGenome() {
        if (MathUtils.getRandom(2) == 0) {
            return paternalGenome;
        } else {
            return maternalGenome;
        }
    }

}
