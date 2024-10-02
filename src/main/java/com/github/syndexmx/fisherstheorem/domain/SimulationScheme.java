package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class SimulationScheme {

    @Getter
    private Integer populationSize;

    @Getter
    private Integer populationLimit;

    @Getter
    private Double reproductionFactor;

    @Getter
    private Integer generationsLimit;

    @Getter
    private MutationProfile mutationProfile;

    public SimulationScheme(int populationSize, int populationLimit, double reproductionFactor,
                            int generationsLimit, MutationProfile mutationProfile) {
        this.populationSize = populationSize;
        this.populationLimit = populationLimit;
        this.reproductionFactor = reproductionFactor;
        this.generationsLimit = generationsLimit;
        this.mutationProfile = mutationProfile;
    }

}
