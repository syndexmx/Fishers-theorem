package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.entities.MutationProfileEntity;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.repositories.MutationProfileRepository;
import com.github.syndexmx.fisherstheorem.repositories.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SimulationMonitoringService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    MutationProfileRepository mutationProfileRepository;

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
                    .fitness(1)
                    .firstQuartDfDt(0)
                    .lastQuartDfDt(0)
                    .build());
        return resultsEntity;
    }

    private MutationProfileEntity readMutationProfileEntity(Long simulationId) {
        MutationProfileEntity mutationProfile = mutationProfileRepository.findById(simulationId).orElse(
                MutationProfileEntity.builder()
                        .beneficialMutationsRate(0.0)
                        .beneficialMutationsEffect(0.0)
                        .deleteriousMutationsRate(0.0)
                        .deleteriousMutationsEffect(0.0)
                        .build());
        return mutationProfile;
    }

    public String getGenerations(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        Integer generationIndex = resultsEntity.getGeneration();
        String generationString;
        if (generationIndex == 0) {
            generationString = "Симуляция запускается. Обновите немного позже \n";
        } else {
            generationString = generationIndex + "\n";
        }
        return  generationString;
    }

    public String getFitness(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getFitness() + "\n";
    }

    public String getStartDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getFirstQuartDfDt() + "\n";
    }

    public Double getEndDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getLastQuartDfDt();
    }

    public Double getBeneficialMutationRate(Long simulationId) {
        MutationProfileEntity mutationProfileEntity = readMutationProfileEntity(simulationId);
        return mutationProfileEntity.getBeneficialMutationsRate();
    }

    public Double getBeneficialMutationEffect(Long simulationId) {
        MutationProfileEntity mutationProfileEntity = readMutationProfileEntity(simulationId);
        return mutationProfileEntity.getBeneficialMutationsEffect();
    }

    public Double getDeleteriousMutationRate(Long simulationId) {
        MutationProfileEntity mutationProfileEntity = readMutationProfileEntity(simulationId);
        return mutationProfileEntity.getDeleteriousMutationsRate();
    }

    public Double getDeleteriousMutationEffect(Long simulationId) {
        MutationProfileEntity mutationProfileEntity = readMutationProfileEntity(simulationId);
        return mutationProfileEntity.getDeleteriousMutationsEffect();
    }

}
