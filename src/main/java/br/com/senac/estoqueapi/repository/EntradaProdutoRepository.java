package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.EntradaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaProdutoRepository extends JpaRepository<EntradaProduto, Integer> {

    List<EntradaProduto> findByEntradaProdutoStatusNot(Integer status);

    Optional<EntradaProduto> findByEntradaProdutoIdAndEntradaProdutoStatusNot(Integer entradaProdutoId, Integer status);
}
