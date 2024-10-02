package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GenomeScheme {

    @Getter
    private TreeMap<Integer, Integer> scheme;

    public GenomeScheme(GenomeConfig config) {
        this.scheme = new TreeMap<Integer, Integer>();
        for (Integer index : config.getMapChromosomeToGenes().keySet()) {
            actualGenes = config.getMapChromosomeToGenes().get(index)
                    / config.getGeneNumberReductionFactor();
            this.scheme.put(index, actualGenes);
        }
    }

    @Getter
    int actualGenes = 0;

}
