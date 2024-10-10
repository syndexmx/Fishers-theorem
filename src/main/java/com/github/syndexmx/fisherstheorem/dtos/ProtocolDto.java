package com.github.syndexmx.fisherstheorem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProtocolDto {

    private Long simulation;

    private int generation;

    private double fitness;

}