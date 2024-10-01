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

    private TreeMap<Integer, Integer> scheme;

    GenomeScheme(GenomeConfig config) {
        this.scheme = new TreeMap<Integer, Integer>();
        for (Integer index : config.getMapChromosomeToGenes().keySet()) {
            this.scheme.put(index, config.getMapChromosomeToGenes().get(index));
        }
    }

}
