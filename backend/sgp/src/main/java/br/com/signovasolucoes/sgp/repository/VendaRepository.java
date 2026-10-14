package br.com.signovasolucoes.sgp.repository;

import br.com.signovasolucoes.sgp.model.Cliente;
import br.com.signovasolucoes.sgp.model.Empresa;
import br.com.signovasolucoes.sgp.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    // Busca unica
    Optional<Venda> findByProtocoloSyngular(String protocoloSyngular);
    Optional<Venda> findByPedidoCcn(String pedidoCcn);

    // Duplicidade no cadastro
    boolean existsByProtocoloSyngular(String protocoloSyngular);
    boolean existsByPedidoCcn(String pedidoCcn);


    List<Venda> findByStatus(Venda.Status status);
    List<Venda> findByPontoVenda(Venda.PontoVenda pontoVenda);

    // Buscar ordenada crescente
    List<Venda> findByStatusOrderByDataVendaDescValorVendidoDesc(Venda.Status status);
    List<Venda> findByClienteIdOrderByDataVendaDescValorVendidoDesc(Long clienteId);
    List<Venda> findByEmpresaIdOrderByDataVendaDescValorVendidoDesc(Long empresaId);
    List<Venda> findByParceiroContainingIgnoreCaseOrderByDataVendaDescValorVendidoDesc(String parceiro);
    List<Venda> findByParceiroAndStatusOrderByDataVendaDescValorVendidoDesc(String parceiro, Venda.Status status);
    List<Venda> findByDataVendaBetweenOrderByDataVendaDescValorVendidoDesc(LocalDate inicio, LocalDate fim);

    // Contagem
    long countByParceiro(String parceiro);
    long countByPontoVenda(Venda.PontoVenda pontoVenda);
    long countByDataVenda(LocalDate inicio, LocalDate fim);


    // Soma o valor vendido no periodo
    @Query("SELECT SUM(v.valorVendido) FROM Venda v " +
            "WHERE v.dataVenda BETWEEN :inicio AND :fim")
    BigDecimal somaValorVendidoNoPeriodo(@Param("inicio") LocalDate inicio,
                                         @Param("fim") LocalDate fim);


    // Soma filtrada por status; o fechamento chama com Venda.Status.FINALIZADA
    @Query("SELECT SUM(v.valorVendido) FROM Venda v " +
            "WHERE v.status = :status AND v.dataVenda BETWEEN :inicio AND :fim")
    BigDecimal somaValorVendidoNoPeriodoPorStatus(@Param("status") Venda.Status status,
                                                  @Param("inicio") LocalDate inicio,
                                                  @Param("fim") LocalDate fim);
}
