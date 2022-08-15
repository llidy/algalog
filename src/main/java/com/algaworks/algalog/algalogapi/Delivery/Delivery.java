package com.algaworks.algalog.algalogapi.Delivery;

import com.algaworks.algalog.algalogapi.Customers.Customer;
import com.algaworks.algalog.algalogapi.addressee.Addressee;
import com.algaworks.algalog.algalogapi.exception.BusinessException;
import com.algaworks.algalog.algalogapi.occurrence.Occurrence;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Customer customer;

    @NotNull
    @Embedded
    @Column(name = "endereço_entrega")
    private Addressee addressee;

    @NotNull
    @Column(name = "taxa")
    private BigDecimal rate;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusDelivery status;

    @Column(name = "data_pedido")
    private OffsetDateTime dateStart;

    @Column(name = "data_finalizacao")
    private OffsetDateTime dateFinal;

    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setDateRecord(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrences().add(occurrence);

        return occurrence;
    }

    public void finish(Delivery delivery) {
        if (cannotBeFinished()) {
            throw new BusinessException("Entrega não pode ser finalizada");
        }
        setStatus(StatusDelivery.FINISHED);
        setDateFinal(OffsetDateTime.now());
    }

    public boolean canBeFinished() {
        return StatusDelivery.PENDING.equals(getStatus());
    }

    public boolean cannotBeFinished() {
        return !canBeFinished();
    }


}
