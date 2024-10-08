package com.github.syndexmx.fisherstheorem.controllersapi;

import com.github.syndexmx.fisherstheorem.dtos.ResultsDto;
import com.github.syndexmx.fisherstheorem.services.SimulationMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MonitorApiController {

    @Autowired
    SimulationMonitoringService simulationMonitoringService;

    @GetMapping("/api/simulation/{simulationid}")
    public ResponseEntity<ResultsDto> getSimulationScheme(@PathVariable final Long simulationid) {
        ResultsDto results = simulationMonitoringService.getResults(simulationid);
        return new ResponseEntity(results, HttpStatus.FOUND);
    }
}
