package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import com.github.syndexmx.fisherstheorem.domain.GenomeScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    GenomeConfig genomeConfig;

    private GenomeScheme genomeScheme;

    public void simulate() {
        int geneCount = 0;
        genomeScheme = new GenomeScheme(genomeConfig);
        for (Integer chromosomeNumber : genomeScheme.getScheme().keySet()) {
            geneCount += genomeScheme.getScheme().get(chromosomeNumber);
        }
        System.out.print("Gene count: ");
        System.out.println(geneCount);
    }

}
