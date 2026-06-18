package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.VendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Integer> {

    List<VendaProduto> findByVendaProdutoStatusNot(Integer status);

    Optional<VendaProduto> findByVendaProdutoIdAndVendaProdutoStatusNot(Integer vendaProdutoId, Integer status);
}
