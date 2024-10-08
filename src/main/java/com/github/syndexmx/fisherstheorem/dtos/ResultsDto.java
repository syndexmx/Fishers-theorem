package com.github.syndexmx.fisherstheorem.dtos;

import com.github.syndexmx.fisherstheorem.domain.Simulation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public class ResultsDto {


        int generation;

        double fitness;

        double firstQuartDfDt;

        double lastQuartDfDt;

    }


