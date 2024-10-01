package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    GenomeConfig genomeConfig;

    public void simulate() {
        int geneCount = 0;
        for (Integer chromosomeNumber : genomeConfig.getMapChromosomeToGenes().keySet()) {
            geneCount += genomeConfig.getMapChromosomeToGenes().get(chromosomeNumber);
        }
        System.out.print("Gene count: ");
        System.out.println(geneCount);
    }

}
