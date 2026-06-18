package br.com.senac.estoqueapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer produtoId;

    @Column(name = "produto_nome", nullable = false, length = 100)
    private String produtoNome;

    @Column(name = "produto_quantidade", nullable = false)
    private Integer produtoQuantidade;

    @Column(name = "produto_preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal produtoPreco;

    @Column(name = "produto_codigo", nullable = false, unique = true)
    private Integer produtoCodigo;

    @Column(name = "produto_data_validade")
    private LocalDate produtoDataValidade;

    @Column(name = "produto_status", nullable = false)
    private Integer produtoStatus;

    @PrePersist
    public void prePersist() {
        if (produtoQuantidade == null) {
            produtoQuantidade = 0;
        }
        if (produtoStatus == null) {
            produtoStatus = 1;
        }
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public void setProdutoQuantidade(Integer produtoQuantidade) {
        this.produtoQuantidade = produtoQuantidade;
    }

    public BigDecimal getProdutoPreco() {
        return produtoPreco;
    }

    public void setProdutoPreco(BigDecimal produtoPreco) {
        this.produtoPreco = produtoPreco;
    }

    public Integer getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(Integer produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public LocalDate getProdutoDataValidade() {
        return produtoDataValidade;
    }

    public void setProdutoDataValidade(LocalDate produtoDataValidade) {
        this.produtoDataValidade = produtoDataValidade;
    }

    public Integer getProdutoStatus() {
        return produtoStatus;
    }

    public void setProdutoStatus(Integer produtoStatus) {
        this.produtoStatus = produtoStatus;
    }
}
