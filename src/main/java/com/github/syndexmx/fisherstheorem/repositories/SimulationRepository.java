package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.entities.SimulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationEntity, Long> {
}
