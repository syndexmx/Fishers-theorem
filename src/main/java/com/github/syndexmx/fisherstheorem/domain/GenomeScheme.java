package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GenomeScheme {

    @Getter
    private List<Integer> scheme;

    @Getter
    private Map<Integer, Integer> geneToChromosomeMap;

    @Getter
    int genesOverall = 0;

    public GenomeScheme(Map<Integer, Integer> genomeMap, Integer geneNumberReductionFactor) {
        TreeMap<Integer, Integer> schemeTreeMap = new TreeMap<Integer, Integer>();
        geneToChromosomeMap = new HashMap<Integer, Integer>();
        for (Integer index : genomeMap.keySet()) {
            int actualGenesOnIndexthChromosome = 0;
            actualGenesOnIndexthChromosome = genomeMap.get(index)
                    / geneNumberReductionFactor;
            schemeTreeMap.put(index, actualGenesOnIndexthChromosome);
            for (int i = genesOverall; i < actualGenesOnIndexthChromosome + genesOverall; i++) {
                geneToChromosomeMap.put(i, index - 1);
                // -1 because chromosomes are numbered starting from 1 in properties
            }
            genesOverall += actualGenesOnIndexthChromosome;
        }
        scheme = new ArrayList<Integer>();
        for (Integer index : schemeTreeMap.keySet()) {
            scheme.add(schemeTreeMap.get(index));
        }
    }


}
