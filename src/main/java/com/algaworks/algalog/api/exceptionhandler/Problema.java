package com.algaworks.algalog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problema {
    private int status;
    private LocalDateTime dateTime;
    private String titulo;

    private List<Campo> campos;
}
