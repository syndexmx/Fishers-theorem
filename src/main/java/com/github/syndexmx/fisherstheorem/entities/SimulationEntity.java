package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "simulations")
public class SimulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sim_id")
    Long simulationId;

    @JoinColumn(name = "scheme_id")
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    SimulationSchemeEntity simulationSchemeEntity;

    @JoinColumn(name = "mut_profile_id")
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    MutationProfileEntity mutationProfileEntity;

    @JoinColumn(name = "results_id")
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    ResultsEntity resultsEntity;


}
