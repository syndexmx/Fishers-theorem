package com.github.syndexmx.fisherstheorem.controllersapi;

import com.github.syndexmx.fisherstheorem.domain.Protocol;
import com.github.syndexmx.fisherstheorem.dtos.ProtocolDto;
import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ProtocolApiController {

    @Autowired
    @Lazy
    SimulationMonitoringService simulationMonitoringService;

    @GetMapping(path = "/api/protocols/{simulationId}")
    public ResponseEntity<List<ProtocolDto>> getProtocolsOfSimulation(@PathVariable Long simulationId) {
        List<Protocol> listProtocols = simulationMonitoringService.getProtocols(simulationId);
        List<ProtocolDto> listProtocolDtos = listProtocols.stream().map(protocol
                -> protocolToProtocolDto(protocol)).toList();
        return new ResponseEntity<>(listProtocolDtos, HttpStatus.FOUND);
    }

    private ProtocolDto protocolToProtocolDto(Protocol protocol) {
        return ProtocolDto.builder()
                .simulation(protocol.getSimulation())
                .generation(protocol.getGeneration())
                .fitness(protocol.getFitness())
                .build();
    }
}
