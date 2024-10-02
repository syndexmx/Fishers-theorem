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

    public SimulationScheme(int populationSize, int populationLimit, double reproductionFactor,
                            int generationsLimit) {
        this.populationSize = populationSize;
        this.populationLimit = populationLimit;
        this.reproductionFactor = reproductionFactor;
        this.generationsLimit = generationsLimit;


    }

}
