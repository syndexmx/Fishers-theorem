package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ResultsEntity {

    @Id
    @Column(name = "results_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long simulationEntity;

    @Column(name = "generations")
    int generation;

    @Column(name = "fitness")
    double fitness;

    @Column(name = "first_quart_df_dt")
    double firstQuartDfDt;

    @Column(name = "last_quart_df_dt")
    double lastQuartDfDt;

}
