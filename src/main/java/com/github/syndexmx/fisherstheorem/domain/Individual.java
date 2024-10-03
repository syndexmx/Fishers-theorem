package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.utils.MathUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Individual {

    @Setter
    @Getter
    private GenomeScheme genomeScheme;

    @Setter
    @Getter
    private SimulationScheme simulationScheme;

    @Getter
    private Genome paternalGenome;

    @Getter
    private Genome maternalGenome;

    Individual(GenomeScheme genomeScheme, SimulationScheme simulationScheme) {
        this.paternalGenome = new Genome(genomeScheme);
        this.maternalGenome = new Genome(genomeScheme);
        this.genomeScheme = genomeScheme;
        this.simulationScheme = simulationScheme;
        // TO DO Change logging level
        log.warn("Ind-generated");
    }

    // Birth
    public Individual makeChild(Individual mother) {
        // TO DO Change logging level
        log.warn("Sex between " + this.toString() + " and " + mother.toString());
        Individual child = new Individual(genomeScheme, simulationScheme);
        child.genomeScheme = this.getGenomeScheme();
        child.simulationScheme = this.getSimulationScheme();
        // TO DO Change logging level
        log.debug("Child scheme is prepared");
        Genome fromFather = this.getHaploGenome();
        Genome fromMother = mother.getHaploGenome();
        // TO DO recombination and splicing
        child.paternalGenome = fromFather;
        child.maternalGenome = fromMother;
        child.tryToMutate();
        // TO DO Change logging level
        log.warn("Child is born : " + child.toString());
        return child;
    }

    public double collectFitness() {
        return MathUtils.collectFitness(paternalGenome.collectFitness(), maternalGenome.collectFitness());
    }

    public Genome getHaploGenome() {
        if (MathUtils.getRandom(2) == 0) {
            return paternalGenome.clone();
        } else {
            return maternalGenome.clone();
        }
    }

    private void tryToMutate() {
        MutationProfile mutationProfile = simulationScheme.getMutationProfile();
        final int SEEDING_CYCLES_COUNT = 10;
        final double reducedBeneficialRate = mutationProfile.getBeneficialMutationsRate()
                / SEEDING_CYCLES_COUNT;
        final double reducedDeleteriousRate = mutationProfile.getDeleteriousMutationsRate()
                / SEEDING_CYCLES_COUNT;
        final int RANDOMIZATION_AMPLITUDE = 100000000;
        final long beneficialMutationCriterion = Math.round(RANDOMIZATION_AMPLITUDE
                * reducedBeneficialRate);
        final long deleteriousMutationCriterion = Math.round(RANDOMIZATION_AMPLITUDE
                * reducedDeleteriousRate);
        for (int cycle = 0; cycle < SEEDING_CYCLES_COUNT; cycle++) {
            if (MathUtils.getRandom(RANDOMIZATION_AMPLITUDE) < beneficialMutationCriterion) {
                mutate(mutationProfile.getBeneficialMutationsEffect());
            }
            if (MathUtils.getRandom(RANDOMIZATION_AMPLITUDE) < deleteriousMutationCriterion) {
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
