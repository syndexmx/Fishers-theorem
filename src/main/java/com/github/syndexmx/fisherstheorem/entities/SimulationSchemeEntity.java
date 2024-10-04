package com.github.syndexmx.fisherstheorem.entities;

import com.github.syndexmx.fisherstheorem.domain.MutationProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "simulation_schemes")
public class SimulationSchemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheme_id")
    Long schemeId;

    @Column(name = "pop_size")
    private Integer populationSize;

    @Column(name = "pop_limit")
    private Integer populationLimit;

    @Column(name = "r_factor")
    private Double reproductionFactor;

    @Column(name = "gen_limit")
    private Integer generationsLimit;

    @OneToOne
    @JoinColumn (name = "profile_id")
    private MutationProfileEntity mutationProfile;


}
