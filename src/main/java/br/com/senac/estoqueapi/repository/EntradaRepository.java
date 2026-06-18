package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    List<Entrada> findByEntradaStatusNot(Integer status);

    Optional<Entrada> findByEntradaIdAndEntradaStatusNot(Integer entradaId, Integer status);
}
