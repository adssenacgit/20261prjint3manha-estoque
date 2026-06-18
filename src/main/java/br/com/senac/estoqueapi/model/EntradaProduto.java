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

@Entity
@Table(name = "entrada_produto")
public class EntradaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrada_produto_id")
    private Integer entradaProdutoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrada_id", nullable = false)
    private Entrada entrada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCompra;

    @Column(name = "entrada_produto_status")
    private Integer entradaProdutoStatus;

    @PrePersist
    public void prePersist() {
        if (entradaProdutoStatus == null) {
            entradaProdutoStatus = 1;
        }
    }

    public Integer getEntradaProdutoId() {
        return entradaProdutoId;
    }

    public void setEntradaProdutoId(Integer entradaProdutoId) {
        this.entradaProdutoId = entradaProdutoId;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Integer getEntradaProdutoStatus() {
        return entradaProdutoStatus;
    }

    public void setEntradaProdutoStatus(Integer entradaProdutoStatus) {
        this.entradaProdutoStatus = entradaProdutoStatus;
    }
}
