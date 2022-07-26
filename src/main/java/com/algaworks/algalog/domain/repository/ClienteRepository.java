package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

 List<Cliente> findByNome(String name);
 List<Cliente> findByNomeContaining(String name);
 Optional<Cliente> findByEmail(String email);
}
