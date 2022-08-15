package com.algaworks.algalog.algalogapi.occurrence;

import com.algaworks.algalog.algalogapi.Delivery.Delivery;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Occurrence {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Delivery delivery;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_registro")
    private OffsetDateTime dateRecord;

}
