package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.entities.ProtocolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends JpaRepository<ProtocolEntity, Long> {
}
