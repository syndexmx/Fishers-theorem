package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;

public class Gene implements Cloneable {

    @Getter
    double fitness = 1.0;

    Gene(double fitness) {
        this.fitness = fitness;

    }

    public void mutate(double mutationEffect) {
        fitness +=mutationEffect;
    }
}
