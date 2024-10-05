package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.domain.Results;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
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

    public void saveResults(Long id, Results results) {
        resultsRepository.save(resultsToResultsEntity(id, results));
    }

    private ResultsEntity resultsToResultsEntity(Long id, Results results) {
        ResultsEntity resultsEntity = ResultsEntity.builder()
                .ResultsId(id)
                .simulationEntity(
                        simulationService.simulationToSimulationEntity(
                        results.getSimulation()))
                .generation(results.getGeneration())
                .fitness(results.getFitness())
                .firstHalfDfDt(results.getFirstHalfDfDt())
                .secondHalfDfDt(results.getSecondHalfDfDt())
                .build();
        return resultsEntity;
    }

}
