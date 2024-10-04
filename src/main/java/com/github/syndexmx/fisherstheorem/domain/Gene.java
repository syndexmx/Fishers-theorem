package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;

public class Gene implements Cloneable {

    public static final double STARTING_FITNESS_DEVIATION = 0.0;

    @Getter
    private double fitnessDeviation;

    Gene(double fitnessDeviation) {
        this.fitnessDeviation = fitnessDeviation;
    }

    public void mutate(double mutationEffect) {
        fitnessDeviation +=mutationEffect;
    }

    @Override
    public Gene clone() {
        try {
            Gene clone = (Gene) super.clone();
            clone.fitnessDeviation = this.fitnessDeviation;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
