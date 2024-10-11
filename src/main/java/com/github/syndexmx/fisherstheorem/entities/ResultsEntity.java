package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "first_10th_df_dt")
    double firstTenthDfDt;

    @Column(name = "middle_2_10th_df_dt")
    double middle2TenthDfDt;

    @Column(name = "last_10th_df_dt")
    double lastTenthDfDt;

}
