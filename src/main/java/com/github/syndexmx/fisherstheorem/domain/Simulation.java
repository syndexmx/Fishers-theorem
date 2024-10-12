package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.services.ResultsLoggingService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Simulation {

    @Getter
    Long simulationId;

    @Getter
    private Generation generation;

    @Getter
    private SimulationScheme simulationScheme;

    @Getter
    private GenomeScheme genomeScheme;

    @Getter
    private Results results;

    @Getter
    private List<Double> fitnessDeviationHistory;

    @Setter
    private ResultsLoggingService resultsLoggingService;

    public Simulation(SimulationScheme simulationScheme, GenomeScheme genomeScheme, Results results) {
        this.genomeScheme = genomeScheme;
        this.simulationScheme = simulationScheme;
        this.results = results;
        generation = new Generation(this.simulationScheme, this.genomeScheme);
        fitnessDeviationHistory = new ArrayList<Double>();
        fitnessDeviationHistory.add(generation.getFitnessDeviation());
        log.info("Generation " + generation.getGenerationIndex());
    }

    public void runSimulation(Long simulationId) {
        this.simulationId = simulationId;
        while (generation.getGenerationIndex() < simulationScheme.getGenerationsLimit()) {
            generation = generation.nextGeneration();
            fitnessDeviationHistory.add(generation.getFitnessDeviation());
            logProtocol();
            logResults();
        }
    }

    private void logProtocol() {
        int generationIndex = generation.getGenerationIndex();
        Protocol protocol = Protocol.builder()
                .simulation(simulationId)
                .generation(generationIndex)
                .fitness(1.0 + fitnessDeviationHistory.get(generationIndex))
                .build();
        resultsLoggingService.saveProtocol(simulationId, protocol);
    }

    private void logResults() {
        int generationIndex = generation.getGenerationIndex();
        if (generation.getGenerationIndex() > 10) {

            double startPeriodFitnessDev = fitnessDeviationHistory.get(0);
            double firstTenthFitnessDev = fitnessDeviationHistory.get(generationIndex / 10);
            double firstTenthRate = (firstTenthFitnessDev - startPeriodFitnessDev)
                    / (generationIndex / 10);

            int fourthIndex = generationIndex-  generationIndex / 2 - generationIndex / 10;
            double fourthTenthFitnessDev = fitnessDeviationHistory.get(fourthIndex);
            int sixthIndex = generationIndex -  generationIndex / 2 + generationIndex / 10;
            double sixthTenthFitnessDev = fitnessDeviationHistory.get(sixthIndex);
            double middle2TenthRate = (sixthTenthFitnessDev - fourthTenthFitnessDev)
                    / (sixthIndex -  fourthIndex);

            double beforeLastTenthFitnessDev = fitnessDeviationHistory.get(generationIndex
                    - generationIndex / 10);
            double endPeriodFitnessDev = fitnessDeviationHistory.get(generationIndex);
            double lastQuartRate = (endPeriodFitnessDev - beforeLastTenthFitnessDev)
                    / (generationIndex - generationIndex /10);

            double firstHalfD2fDt2 = (middle2TenthRate - firstTenthRate) / (generationIndex / 2.0);

            double secondHalfD2fDt2 = (lastQuartRate - middle2TenthRate) / (generationIndex / 2.0);

            Results results = Results.builder()
                    .id(simulationId)
                    .simulation(this)
                    .generation(generationIndex)
                    .fitness(1.0 + endPeriodFitnessDev)
                    .firstTenthDfDt(firstTenthRate)
                    .middle2TenthDfDt(middle2TenthRate)
                    .lastTenthDfDt(lastQuartRate)
                    .firstHalfD2fDt2(firstHalfD2fDt2)
                    .secondHalfD2fDt2(secondHalfD2fDt2)
                    .build();
            this.results = results;
            resultsLoggingService.saveResults(simulationId, results);
            log.info("Generation " + generation.getGenerationIndex()
                    + " fitness = " + (1.0 + generation.getFitnessDeviation()));
        } else {
            log.info("Generation " + generation.getGenerationIndex());
        }
    }
}
