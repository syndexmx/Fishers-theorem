package com.github.syndexmx.fisherstheorem.dtos;

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

        double firstTenthDfDt;

        double middle2TenthDfDt;

        double lastTenthDfDt;

    }


