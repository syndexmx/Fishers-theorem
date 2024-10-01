package com.github.syndexmx.fisherstheorem.domain;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {

    List<Gene> genes;

    public Chromosome(Integer numberGenes) {
        List<Gene> genes = new ArrayList<Gene>();
        for (int i = 0; i < numberGenes; i++) {
            genes.add(new Gene(1.0));
        }
    }
}
