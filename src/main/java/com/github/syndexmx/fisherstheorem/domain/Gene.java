package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;
import lombok.Setter;

public class Gene implements Cloneable {

    @Getter
    @Setter
    private double fitness = 1.0;

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
            clone.setFitness(this.fitness);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
