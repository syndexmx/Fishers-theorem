package com.github.syndexmx.fisherstheorem.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Builder
public class SimulationScheme {

    @Getter
    private Integer populationLimit;

    @Getter
    private Integer generationsLimit;

    @Getter
    private MutationProfile mutationProfile;

    public SimulationScheme(int populationLimit, int generationsLimit,
                            MutationProfile mutationProfile) {
        this.populationLimit = populationLimit;
        this.generationsLimit = generationsLimit;
        this.mutationProfile = mutationProfile;
    }

}
