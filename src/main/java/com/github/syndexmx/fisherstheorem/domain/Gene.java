package com.github.syndexmx.fisherstheorem.domain;

import lombok.Getter;

public class Gene implements Cloneable {

    public static final int STARTING_FITNESS_DEVIATION = 0;

    @Getter
    private int fitnessDeviation; // in arbitrary small integer steps

    Gene(int fitnessDeviation) {
        this.fitnessDeviation = fitnessDeviation;
    }

    public void mutate(int mutationEffect) {
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
