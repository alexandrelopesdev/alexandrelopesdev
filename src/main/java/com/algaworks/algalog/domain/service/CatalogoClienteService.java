package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
       boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
               .stream()
               .anyMatch(clientExistente -> !clientExistente.equals(cliente));

       if (emailEmUso){
           throw new NegocioException("Ja existe um clente " +
                   "cadastrado com este email");
       }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long clientId){
        clienteRepository.deleteById(clientId);
    }
}
