package com.github.syndexmx.fisherstheorem.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("simulation.properties")
public class SimulationConfig {

    @Value("${population.size}")
    @Getter
    Integer populationSize;

    @Value("${population.limit}")
    @Getter
    Integer populationLimit;

    @Value("${reproductionfactor.percent}")
    @Getter
    Double reproductionFactor;

    @Value("${simulation.generations.limit}")
    @Getter
    Integer generationsLimit;

}
