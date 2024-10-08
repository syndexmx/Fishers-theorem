package com.github.syndexmx.fisherstheorem.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Builder
public class MutationProfile {


    @Getter
    private Double beneficialMutationsRate;

    @Getter
    private Double beneficialMutationsEffect;

    @Getter
    private Double deleteriousMutationsRate;

    @Getter
    private Double deleteriousMutationsEffect;

    public MutationProfile(Double beneficialMutationsRate,
                           Double beneficialMutationsEffect,
                           Double deleteriousMutationsRate,
                           Double deleteriousMutationsEffect) {
        this.beneficialMutationsRate = beneficialMutationsRate;
        this.beneficialMutationsEffect = beneficialMutationsEffect;
        this.deleteriousMutationsRate = deleteriousMutationsRate;
        this.deleteriousMutationsEffect = deleteriousMutationsEffect;
    }
}
