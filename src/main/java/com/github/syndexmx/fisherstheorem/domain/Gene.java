package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import lombok.Setter;

public class Gene implements Cloneable {

    public static final double STARTING_FITNESS = 0.0;

    @Getter
    private double fitness;

    Gene(double fitness) {
        this.fitness = fitness;
    }

    public void mutate(double mutationEffect) {
        fitness +=mutationEffect;
    }

    @Override
    public Gene clone() {
        try {
            Gene clone = (Gene) super.clone();
            clone.fitness = this.fitness;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
