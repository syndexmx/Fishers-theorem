package com.github.syndexmx.fisherstheorem.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("simulation.properties")
public class SimulationConfig {

    @Value("${population.size}")
    Integer populationSize;

    @Value("${population.limit}")
    Integer populationLimit;

}
