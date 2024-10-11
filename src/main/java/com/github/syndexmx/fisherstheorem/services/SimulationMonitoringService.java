package com.github.syndexmx.fisherstheorem.services;

import com.github.syndexmx.fisherstheorem.domain.Protocol;
import com.github.syndexmx.fisherstheorem.dtos.ResultsDto;
import com.github.syndexmx.fisherstheorem.entities.MutationProfileEntity;
import com.github.syndexmx.fisherstheorem.entities.ProtocolEntity;
import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import com.github.syndexmx.fisherstheorem.repositories.MutationProfileRepository;
import com.github.syndexmx.fisherstheorem.repositories.ProtocolRepository;
import com.github.syndexmx.fisherstheorem.repositories.ResultsRepository;
import com.github.syndexmx.fisherstheorem.repositories.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class SimulationMonitoringService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    MutationProfileRepository mutationProfileRepository;

    @Autowired
    @Lazy
    SimulationService simulationService;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    ProtocolRepository protocolRepository;

    public String getSimulationId(Long simulationId) {
        return simulationId.toString();
    }

    public List<Protocol> getProtocols(Long simulationId) {
        SimulationEntity simulationEntity =  simulationRepository.findById(simulationId).get();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("sim_id", exact())
                .withIgnorePaths("protocol_item_id", "generation", "fitness");
        Example<ProtocolEntity> example = Example.of(ProtocolEntity.builder()
                .simulationEntity(simulationEntity).build(), matcher);
        List<ProtocolEntity> listProtocolEntities = protocolRepository.findAll(example);
        List<Protocol> listProtocols = listProtocolEntities.stream().map(protocolEntity
                -> protocolEntityToProtocol(protocolEntity)).toList();
        return listProtocols;
    }

    private Protocol protocolEntityToProtocol(ProtocolEntity protocolEntity) {
        return Protocol.builder()
                .simulation(protocolEntity.getSimulationEntity().getSimulationId())
                .generation(protocolEntity.getGeneration())
                .fitness(protocolEntity.getFitness())
                .build();
    }

    private ResultsEntity readResultsEntity(Long simulationId) {
        ResultsEntity resultsEntity = resultsRepository.findById(simulationId).orElse(
                ResultsEntity.builder()
                    .generation(0)
                    .fitness(1)
                    .firstTenthDfDt(0)
                    .middle2TenthDfDt(0)
                    .lastTenthDfDt(0)
                    .build());
        return resultsEntity;
    }

    public ResultsDto getResults(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return ResultsDto.builder()
                .generation(resultsEntity.getGeneration())
                .fitness(resultsEntity.getFitness())
                .firstTenthDfDt(resultsEntity.getFirstTenthDfDt())
                .middle2TenthDfDt(resultsEntity.getMiddle2TenthDfDt())
                .lastTenthDfDt(resultsEntity.getLastTenthDfDt())
                .build();
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
        return resultsEntity.getFirstTenthDfDt() + "\n";
    }

    public String getMiddleDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getMiddle2TenthDfDt() + "\n";
    }

    public Double getEndDfDt(Long simulationId) {
        ResultsEntity resultsEntity = readResultsEntity(simulationId);
        return resultsEntity.getLastTenthDfDt();
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
