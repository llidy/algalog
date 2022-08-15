package com.algaworks.algalog.algalogapi.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVErsionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }
}
