package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Entrega>  solicitar(@RequestBody Entrega entrega){
        entrega = solicitacaoEntregaService.solicitar(entrega);

        return ResponseEntity.ok(entrega);
    }
}
