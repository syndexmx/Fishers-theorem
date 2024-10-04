package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.domain.SimulationScheme;
import com.github.syndexmx.fisherstheorem.entities.SimulationSchemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationSchemeRepository extends JpaRepository<SimulationSchemeEntity, Long> {
}
