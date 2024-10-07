package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "mutation_profiles")
public class MutationProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mut_profile_id")
    Long mutationProfileId;

    @Column(name = "benef_effect")
    private Double beneficialMutationsEffect;

    @Column(name = "benef_rate")
    private Double beneficialMutationsRate;

    @Column(name = "delet_effect")
    private Double deleteriousMutationsEffect;

    @Column(name = "delet_rate")
    private Double deleteriousMutationsRate;
}
