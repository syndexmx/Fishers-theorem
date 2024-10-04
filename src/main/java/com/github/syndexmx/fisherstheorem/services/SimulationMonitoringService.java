package com.github.syndexmx.fisherstheorem.services;

import org.springframework.stereotype.Service;

@Service
public class SimulationMonitoringService {

    public String getStatus(Long simulationId) {

        return "Current Status on " + simulationId + ": generation " + " ?? " ;
    }
}
