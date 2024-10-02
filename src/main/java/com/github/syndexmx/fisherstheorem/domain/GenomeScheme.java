package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GenomeScheme {

    @Getter
    private List<Integer> scheme;

    public GenomeScheme(Map<Integer, Integer> genomeMap, Integer geneNumberReductionFactor) {
        TreeMap<Integer, Integer> schemeTreeMap = new TreeMap<Integer, Integer>();
        for (Integer index : genomeMap.keySet()) {
            actualGenes = genomeMap.get(index)
                    / geneNumberReductionFactor;
            schemeTreeMap.put(index, actualGenes);
        }
        scheme = new ArrayList<Integer>();
        for (Integer index : schemeTreeMap.keySet()) {
            scheme.add(schemeTreeMap.get(index));
        }
    }

    @Getter
    int actualGenes = 0;

}
