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

    public String getStatus(Long simulationId) {
        ResultsEntity resultsEntity = resultsRepository.findById(simulationId).get();
        StringBuilder result = new StringBuilder();
        result.append("Состояние симуляции <<" + simulationId + ">>\n");
        result.append(" поколение: " + resultsEntity.getGeneration() + "; \n");
        result.append(" приспособленность(fitness): " + resultsEntity.getFitness() + "; \n");
        result.append(" скорость изменения приспособленности ");
        result.append(" df/dt (по первой четверти времени): " + resultsEntity.getFirstQuartDfDt() + "; \n");
        result.append(" скорость изменения приспособленности ");
        result.append(" df/dt (по последней четверти времени): " + resultsEntity.getLastQuartDfDt() + "; \n");
        return result.toString() ;
    }
}
