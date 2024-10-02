package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;

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

    // Birth
    public Individual(Individual father, Individual mother, MutationProfile mutationProfile) {
        Genome fromFather = father.getHaploGenome();
        Genome fromMother = mother.getHaploGenome();
        // TO DO splicing
        paternalGenome = fromFather;
        maternalGenome = fromMother;
        this.mutationProfile = mutationProfile;
        tryToMutate();
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

    private void tryToMutate() {
        final int SEEDING_CYCLES_COUNT = 10;
        final double REDUCED_BENEFICIAL_RATE = mutationProfile.getBeneficialMutationsRate()
                / SEEDING_CYCLES_COUNT;
        final double REDUCED_DELETERIOUS_RATE = mutationProfile.getDeleteriousMutationsRate()
                / SEEDING_CYCLES_COUNT;
        final int RANDOMIZATION_AMPLITUDE = 100000000;
        final long BENEFICIAL_MUTATION_CRITERION = Math.round(RANDOMIZATION_AMPLITUDE
                * REDUCED_BENEFICIAL_RATE);
        final long DELETERIOUS_MUTATION_CRITERION = Math.round(RANDOMIZATION_AMPLITUDE
                * REDUCED_DELETERIOUS_RATE);
        for (int cycle = 0; cycle < SEEDING_CYCLES_COUNT; cycle++) {
            if (MathUtils.getRandom(RANDOMIZATION_AMPLITUDE) < BENEFICIAL_MUTATION_CRITERION) {
                mutate(mutationProfile.getBeneficialMutationsEffect());
            }
            if (MathUtils.getRandom(RANDOMIZATION_AMPLITUDE) < DELETERIOUS_MUTATION_CRITERION) {
                mutate(-mutationProfile.getDeleteriousMutationsEffect());
            }
        }
    }

    private void mutate(double mutationEffect) {
        if (MathUtils.getRandom(2) == 0) {
            paternalGenome.mutate(mutationEffect);
        } else {
            maternalGenome.mutate(mutationEffect);
        }
    }

}
