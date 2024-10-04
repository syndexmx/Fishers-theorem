package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.entities.MutationProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutationProfileRepository extends JpaRepository<MutationProfileEntity, Long> {
}
