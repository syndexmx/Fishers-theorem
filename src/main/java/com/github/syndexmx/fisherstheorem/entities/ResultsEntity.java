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
    Long ResultsId;

    @JoinColumn (name = "sim_id")
    @OneToOne (fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    SimulationEntity simulationEntity;

    @Column(name = "generations")
    int generation;

    @Column(name = "fitness")
    double fitness;

    @Column(name = "first_quart_df_dt")
    double firstQuartDfDt;

    @Column(name = "last_quart_df_dt")
    double lastQuartDfDt;

}
