package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.configurations.GenomeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GenomeScheme {

    private Map<Integer, Integer> scheme;

    GenomeScheme(GenomeConfig config) {
        this.scheme = config.getMapChromosomeToGenes();
    }

}
