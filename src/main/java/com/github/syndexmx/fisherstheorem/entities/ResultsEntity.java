package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultsEntity {

    @Id
            @Column(name = "results_id")
            @GeneratedValue(strategy = GenerationType.AUTO)
    Long ResultsId;

    @JoinColumn (name = "sim_id")
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    SimulationEntity simulationEntity;

    @Column(name = "generations")
    int generation;

    @Column(name = "fitness")
    double fitness;

    @Column(name = "first_half_df_dt")
    double firstHalfDfDt;

    @Column(name = "second_half_df_dt")
    double secondHalfDfDt;

}
