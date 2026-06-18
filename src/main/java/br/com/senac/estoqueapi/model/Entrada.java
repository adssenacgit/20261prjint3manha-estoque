package br.com.senac.estoqueapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrada_id")
    private Integer entradaId;

    @Column(name = "entrada_data", nullable = false)
    private LocalDateTime entradaData;

    @Column(name = "entrada_fornecedor", length = 100)
    private String entradaFornecedor;

    @Column(name = "entrada_valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal entradaValorTotal;

    @Column(name = "entrada_status")
    private Integer entradaStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        if (entradaData == null) {
            entradaData = LocalDateTime.now();
        }
        if (entradaStatus == null) {
            entradaStatus = 1;
        }
    }

    public Integer getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Integer entradaId) {
        this.entradaId = entradaId;
    }

    public LocalDateTime getEntradaData() {
        return entradaData;
    }

    public void setEntradaData(LocalDateTime entradaData) {
        this.entradaData = entradaData;
    }

    public String getEntradaFornecedor() {
        return entradaFornecedor;
    }

    public void setEntradaFornecedor(String entradaFornecedor) {
        this.entradaFornecedor = entradaFornecedor;
    }

    public BigDecimal getEntradaValorTotal() {
        return entradaValorTotal;
    }

    public void setEntradaValorTotal(BigDecimal entradaValorTotal) {
        this.entradaValorTotal = entradaValorTotal;
    }

    public Integer getEntradaStatus() {
        return entradaStatus;
    }

    public void setEntradaStatus(Integer entradaStatus) {
        this.entradaStatus = entradaStatus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
