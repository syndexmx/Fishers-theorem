package com.github.syndexmx.fisherstheorem.domain;

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

    double firstTenthDfDt;

    double middle2TenthDfDt;

    double lastTenthDfDt;

    double firstHalfD2fDt2;

    double secondHalfD2fDt2;


}
