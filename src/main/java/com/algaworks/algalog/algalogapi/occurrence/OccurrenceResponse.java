package com.algaworks.algalog.algalogapi.occurrence;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OccurrenceResponse {

    private Long id;

    private String description;

    private OffsetDateTime dateRecord;
}
