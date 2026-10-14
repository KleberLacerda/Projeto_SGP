package br.com.signovasolucoes.sgp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PontoVenda pontoVenda;

    @NotBlank(message = "Informe o parceiro indicador.")
    private String parceiro;

    @NotNull(message = "Informe o tipo de certificado.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCertificado tipoCertificado;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @NotNull
    private BigDecimal valorVendido;

    @NotNull(message = "Informe a forma de pagamento.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaDePagamento formaDePagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeAtendimento tipoDeAtendimento;

    @NotBlank(message = "Informe o procotolo Syngular.")
    @Column(unique = true)
    private String protocoloSyngular;

    @NotBlank(message = "Informe o número do pedido CCN.")
    @Column(unique = true)
    private String pedidoCcn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDENTE;

    public enum PontoVenda {
        SIGNOVA, AKAT
    }

    public enum TipoCertificado {ECPF, ECNPJ}

    public enum FormaDePagamento {
        PIX, BOLETO, CREDITO
    }

    public enum TipoDeAtendimento {
        PRESENCIAL, ONLINE, VIDEO
    }

    public enum Status {
        PENDENTE, CANCELADA, FINALIZADA
    }

    public Venda() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PontoVenda getPontoVenda() {
        return pontoVenda;
    }

    public void setPontoVenda(PontoVenda pontoVenda) {
        this.pontoVenda = pontoVenda;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getValorVendido() {
        return valorVendido;
    }

    public void setValorVendido(BigDecimal valorVendido) {
        this.valorVendido = valorVendido;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public TipoDeAtendimento getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    public String getProtocoloSyngular() {
        return protocoloSyngular;
    }

    public void setProtocoloSyngular(String protocoloSyngular) {
        this.protocoloSyngular = protocoloSyngular;
    }

    public String getPedidoCcn() {
        return pedidoCcn;
    }

    public void setPedidoCcn(String pedidoCcn) {
        this.pedidoCcn = pedidoCcn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TipoCertificado getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(TipoCertificado tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }
}