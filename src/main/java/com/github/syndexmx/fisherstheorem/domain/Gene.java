package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;

@Getter
public class Gene {

    double fitness = 1.0;

    Gene(double fitness) {
        this.fitness = fitness;
    }
}
