package br.com.signovasolucoes.sgp.repository;

import br.com.signovasolucoes.sgp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca cliente
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);
    Optional<Cliente> findByEmail(String email);


    // Cliente existe
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);


}
