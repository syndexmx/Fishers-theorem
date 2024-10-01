package com.github.syndexmx.fisherstheorem.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("genome.properties")
public class GenomeConfig {

    @Value("#{${genome.chromosomesmap}}")
    @Getter
    Map<Integer, Integer> mapChromosomeToGenes;

    @Value("${simulation.genecount.reductionfactor}")
    Integer geneNumberReductionFactor;


}
