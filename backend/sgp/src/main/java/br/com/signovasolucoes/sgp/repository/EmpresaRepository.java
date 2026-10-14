package br.com.signovasolucoes.sgp.repository;


import br.com.signovasolucoes.sgp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByCnpj(String cnpj);
    List<Empresa> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
    List<Empresa> findByClienteId(Long clienteId);
    long countByClienteId(Long clienteId);
    boolean existsByCnpj(String cnpj);


}
