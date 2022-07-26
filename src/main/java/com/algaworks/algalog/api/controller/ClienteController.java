package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algalog.domain.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;


    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();

    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clientId) {

        return clienteRepository.findById(clientId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){

        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = catalogoClienteService.salvar(cliente);

        return  ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void>  remover(@PathVariable Long clienteId){

        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }

}
