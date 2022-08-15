package com.algaworks.algalog.algalogapi.occurrence;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OccurrenceRequest {

    @NotBlank
    private String description;

}
