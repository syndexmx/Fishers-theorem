package com.github.syndexmx.fisherstheorem.domain;

import lombok.Setter;

public class Individual {

    Genome genome;

    enum Sex {
        MALE,
        FEMALE
    }

    @Setter
    Sex sex;

}
