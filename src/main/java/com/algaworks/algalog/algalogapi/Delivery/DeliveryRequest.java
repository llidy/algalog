package com.algaworks.algalog.algalogapi.Delivery;

import com.algaworks.algalog.algalogapi.addressee.AddresseeRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryRequest {

    @NotNull
    private CustomerId customer;

    @NotNull
    private AddresseeRequest addressee;

    @NotNull
    private BigDecimal rate;


}
