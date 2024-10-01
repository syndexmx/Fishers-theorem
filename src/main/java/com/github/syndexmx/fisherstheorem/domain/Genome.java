package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Genome {

    List<Chromosome> chromosomes;

    Genome(GenomeScheme genomeScheme) {
        chromosomes = new ArrayList<Chromosome>();
        for (Integer index : genomeScheme.getScheme().keySet()) {
            Chromosome chromosome = new Chromosome(genomeScheme.getScheme().get(index));

        }
    }

    Genome(Genome prototypeGenome) {

    }
}
