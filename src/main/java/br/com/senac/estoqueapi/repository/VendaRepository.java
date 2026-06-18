package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    List<Venda> findByVendaStatusNot(Integer status);

    Optional<Venda> findByVendaIdAndVendaStatusNot(Integer vendaId, Integer status);
}
