package com.github.syndexmx.fisherstheorem.repositories;

import com.github.syndexmx.fisherstheorem.entities.ResultsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<ResultsEntity, Long> {
}
