package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.domain.Protocol;
import com.github.syndexmx.fisherstheorem.domain.Results;
import com.github.syndexmx.fisherstheorem.entities.ProtocolEntity;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.repositories.ProtocolRepository;
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

    @Autowired
    ProtocolRepository protocolRepository;

    public void saveProtocol(Long id, Protocol protocol) {
        protocolRepository.save(protocolToProtocolEntity(id, protocol));
    }

    public void saveResults(Long id,  Results results) {
        resultsRepository.save(resultsToResultsEntity(id, results));
    }

    private ResultsEntity resultsToResultsEntity(Long id, Results results) {
        SimulationEntity simulationEntity =  simulationRepository.findById(id).get();
        ResultsEntity resultsEntity = ResultsEntity.builder()
                .simulationEntity(id)
                .generation(results.getGeneration())
                .fitness(results.getFitness())
                .firstTenthDfDt(results.getFirstTenthDfDt())
                .middle2TenthDfDt(results.getMiddle2TenthDfDt())
                .lastTenthDfDt(results.getLastTenthDfDt())
                .firstHalfD2fDt2(results.getFirstHalfD2fDt2())
                .secondHalfD2fDt2(results.getSecondHalfD2fDt2())
                .build();
        return resultsEntity;
    }

    private ProtocolEntity protocolToProtocolEntity(Long id, Protocol protocol) {
        SimulationEntity simulationEntity =  simulationRepository.findById(id).get();
        ProtocolEntity protocolEntity =  ProtocolEntity.builder()
                .simulationEntity(simulationEntity)
                .generation(protocol.getGeneration())
                .fitness(protocol.getFitness())
                .build();
        return protocolEntity;
    }

}
