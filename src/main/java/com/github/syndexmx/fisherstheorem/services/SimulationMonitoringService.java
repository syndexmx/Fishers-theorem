package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.repositories.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SimulationMonitoringService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    @Lazy
    SimulationService simulationService;

    public String getSimulationId(Long simulationId) {
        return simulationId.toString();
    }

    private ResultsEntity readResultsEntity(Long simulationId) {
        ResultsEntity resultsEntity = resultsRepository.findById(simulationId).orElse(
                ResultsEntity.builder()
                    .generation(0)
                    .fitness(0)
                    .firstQuartDfDt(0)
                    .lastQuartDfDt(0)
                    .build());
        return resultsEntity;
    }

    public String getGenerations(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getGeneration() + "\n";
    }

    public String getFitness(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getFitness() + "\n";
    }

    public String getStartDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getFirstQuartDfDt() + "\n";
    }

    public String getEndDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getLastQuartDfDt() + "\n";
    }
}
