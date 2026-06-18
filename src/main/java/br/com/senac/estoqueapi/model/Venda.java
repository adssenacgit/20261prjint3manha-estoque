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
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id")
    private Integer vendaId;

    @Column(name = "venda_data", nullable = false)
    private LocalDateTime vendaData;

    @Column(name = "venda_valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal vendaValorTotal;

    @Column(name = "venda_status", nullable = false)
    private Integer vendaStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        if (vendaData == null) {
            vendaData = LocalDateTime.now();
        }
        if (vendaStatus == null) {
            vendaStatus = 1;
        }
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public LocalDateTime getVendaData() {
        return vendaData;
    }

    public void setVendaData(LocalDateTime vendaData) {
        this.vendaData = vendaData;
    }

    public BigDecimal getVendaValorTotal() {
        return vendaValorTotal;
    }

    public void setVendaValorTotal(BigDecimal vendaValorTotal) {
        this.vendaValorTotal = vendaValorTotal;
    }

    public Integer getVendaStatus() {
        return vendaStatus;
    }

    public void setVendaStatus(Integer vendaStatus) {
        this.vendaStatus = vendaStatus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
