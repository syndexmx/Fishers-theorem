package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.domain.Results;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.repositories.ResultsRepository;
import com.github.syndexmx.fisherstheorem.repositories.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ResultsLoggingService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    @Lazy
    SimulationService simulationService;

    @Autowired
    SimulationRepository simulationRepository;

    public void saveResults(Long id, Results results) {

        resultsRepository.save(resultsToResultsEntity(id, results));
    }

    private ResultsEntity resultsToResultsEntity(Long id, Results results) {
        SimulationEntity simulationEntity =  simulationRepository.findById(id).get();
        ResultsEntity resultsEntity = ResultsEntity.builder()
                .simulationEntity(id)
                .generation(results.getGeneration())
                .fitness(results.getFitness())
                .firstQuartDfDt(results.getFirstQuartDfDt())
                .lastQuartDfDt(results.getLastQuartDfDt())
                .build();
        return resultsEntity;
    }

}
