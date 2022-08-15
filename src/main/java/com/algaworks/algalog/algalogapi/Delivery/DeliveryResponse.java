package com.algaworks.algalog.algalogapi.Delivery;

import com.algaworks.algalog.algalogapi.addressee.AddresseeResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryResponse {

    private Long id;

    private String nameCustomer;

    private AddresseeResponse addressee;

    private BigDecimal rate;

    private StatusDelivery status;

    private OffsetDateTime dateStart;

    private OffsetDateTime dateFinal;
}
