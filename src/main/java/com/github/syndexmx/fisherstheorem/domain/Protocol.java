package com.github.syndexmx.fisherstheorem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Protocol {

    private Long simulation;

    private int generation;

    private double fitness;

}