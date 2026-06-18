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
@Table(name = "venda_produto")
public class VendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_produto_id")
    private Integer vendaProdutoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "venda_quantidade", nullable = false)
    private Integer vendaQuantidade;

    @Column(name = "venda_preco_unidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal vendaPrecoUnidade;

    @Column(name = "venda_produto_status")
    private Integer vendaProdutoStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @PrePersist
    public void prePersist() {
        if (vendaProdutoStatus == null) {
            vendaProdutoStatus = 1;
        }
    }

    public Integer getVendaProdutoId() {
        return vendaProdutoId;
    }

    public void setVendaProdutoId(Integer vendaProdutoId) {
        this.vendaProdutoId = vendaProdutoId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getVendaQuantidade() {
        return vendaQuantidade;
    }

    public void setVendaQuantidade(Integer vendaQuantidade) {
        this.vendaQuantidade = vendaQuantidade;
    }

    public BigDecimal getVendaPrecoUnidade() {
        return vendaPrecoUnidade;
    }

    public void setVendaPrecoUnidade(BigDecimal vendaPrecoUnidade) {
        this.vendaPrecoUnidade = vendaPrecoUnidade;
    }

    public Integer getVendaProdutoStatus() {
        return vendaProdutoStatus;
    }

    public void setVendaProdutoStatus(Integer vendaProdutoStatus) {
        this.vendaProdutoStatus = vendaProdutoStatus;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
