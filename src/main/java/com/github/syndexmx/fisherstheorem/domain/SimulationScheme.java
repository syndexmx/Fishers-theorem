package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class SimulationScheme {

    @Getter
    Integer populationSize;

    @Getter
    Integer populationLimit;

    @Getter
    Double reproductionFactor;

    @Getter
    Integer generationsLimit;

    public SimulationScheme(int populationSize, int populationLimit, double reproductionFactor,
                            int generationsLimit) {
        this.populationSize = populationSize;
        this.populationLimit = populationLimit;
        this.reproductionFactor = reproductionFactor;
        this.generationsLimit = generationsLimit;


    }

}
