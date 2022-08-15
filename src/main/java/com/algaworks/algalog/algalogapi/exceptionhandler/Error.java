package com.algaworks.algalog.algalogapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Error {

    private Integer status;

    private OffsetDateTime date;

    private String message;

    private List<Field> fields;

    @Getter
    @AllArgsConstructor
    public static class Field {

        private String field;
        private String message;

    }
}

