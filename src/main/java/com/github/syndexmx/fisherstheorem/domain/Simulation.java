package com.github.syndexmx.fisherstheorem.domain;

import com.github.syndexmx.fisherstheorem.services.ResultsLoggingService;
import lombok.Builder;
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
        if (generation.getGenerationIndex() > 8) {
            double startPeriodFitnessDev = fitnessDeviationHistory.get(0);
            double firstQuartFitnessDev = fitnessDeviationHistory.get(generationIndex / 4);
            double thirdQuartFitnessDev = fitnessDeviationHistory.get(generationIndex - generationIndex / 4);
            double endPeriodFitnessDev = fitnessDeviationHistory.get(generationIndex);
            double firstQuartRate = (firstQuartFitnessDev - startPeriodFitnessDev)
                    / (generationIndex / 4);
            double lastQuartRate = (endPeriodFitnessDev - thirdQuartFitnessDev)
                    / (generationIndex - generationIndex /4);
            Results results = Results.builder()
                    .id(simulationId)
                    .simulation(this)
                    .generation(generationIndex)
                    .fitness(1.0 + endPeriodFitnessDev)
                    .firstQuartDfDt(firstQuartRate)
                    .lastQuartDfDt(lastQuartRate)
                    .build();
            this.results = results;
            resultsLoggingService.saveResults(simulationId, results);
            log.info("Generation " + generation.getGenerationIndex()
                    + " fitness = "
                    + (1.0 + generation.getFitnessDeviation())
                    + "  Long-term fitness change: 1st quart df/dt= " + firstQuartRate
                    + "; 4th quart df/dt= " + lastQuartRate);
        } else {
            log.info("Generation " + generation.getGenerationIndex());
        }
    }
}
