package com.github.syndexmx.fisherstheorem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "protocols")
public class ProtocolEntity {

    @Id
    @Column(name = "protocol_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long protocolItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sim_id")
    SimulationEntity simulationEntity;

    @Column(name = "generation")
    int generation;

    @Column(name = "fitness")
    double fitness;

}
