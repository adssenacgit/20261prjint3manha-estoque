package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByProdutoStatusNot(Integer status);

    Optional<Produto> findByProdutoIdAndProdutoStatusNot(Integer produtoId, Integer status);
}
