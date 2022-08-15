package com.algaworks.algalog.algalogapi.exceptionhandler;

import com.algaworks.algalog.algalogapi.exception.BusinessException;
import com.algaworks.algalog.algalogapi.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Field> fields = new ArrayList<>();

        for (ObjectError errors : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) errors).getField();
            String message = messageSource.getMessage(errors, LocaleContextHolder.getLocale());

            fields.add(new Error.Field(field, message));
        }
        Error error = new Error();
        error.setStatus(status.value());
        error.setDate(OffsetDateTime.now());
        error.setMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        error.setFields(fields);

        return handleExceptionInternal(
                ex,
                error,
                headers,
                status,
                request
        );
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> exception(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDate(OffsetDateTime.now());
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> exceptionEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDate(OffsetDateTime.now());
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
