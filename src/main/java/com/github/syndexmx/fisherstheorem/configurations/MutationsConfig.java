package com.github.syndexmx.fisherstheorem.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("mutations.properties")
public class MutationsConfig {

    @Value("${beneficialmutations.effect}")
    @Getter
    Double beneficialMutationsEffect;

    @Value("${beneficialmutations.rate}")
    @Getter
    Double beneficialMutationsRate;

    @Value("${deleteriousmutations.effect}")
    @Getter
    Double deleteriousMutationsEffect;

    @Value("${deleteriousmutations.rate}")
    @Getter
    Double deleteriousMutationsRate;

}
