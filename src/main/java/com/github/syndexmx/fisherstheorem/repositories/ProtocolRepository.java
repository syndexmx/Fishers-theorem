package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.dtos.ProtocolDto;
import com.github.syndexmx.fisherstheorem.entities.ProtocolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProtocolRepository extends JpaRepository<ProtocolEntity, Long> {

}
