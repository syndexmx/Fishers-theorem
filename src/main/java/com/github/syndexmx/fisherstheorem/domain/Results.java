package com.github.syndexmx.fisherstheorem.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Results {

    Long id;

    Simulation simulation;

    int generation;

    double fitness;

    double firstHalfDfDt;

    double secondHalfDfDt;

}
