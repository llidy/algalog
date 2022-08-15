package com.algaworks.algalog.algalogapi.Delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerId {

    @NotNull
    private Long id;
}
