package com.algaworks.algalog.api.exceptionhandler;

import com.algaworks.algalog.domain.exception.NegocioException;
import org.springframework.context.MessageSource;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String titulo = "";

        titulo = titulo.concat("Um ou mais ")
                .concat("campo estão invalido. ")
                .concat("Faça o prenchimento correto")
                .concat("e tente novamento");

        List<Campo> campos = new ArrayList<>();

        for (ObjectError error : ex
                        .getBindingResult()
                        .getAllErrors()){

            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campos.add(
                    new Campo(
                            nome, mensagem
                    )
            );
        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDateTime(LocalDateTime.now());
        problema.setTitulo(titulo);
        problema.setCampos(campos);


        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDateTime(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex,problema, new HttpHeaders(),status, request);

    }
}
