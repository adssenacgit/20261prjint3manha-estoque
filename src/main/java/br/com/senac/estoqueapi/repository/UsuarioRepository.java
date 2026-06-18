package br.com.senac.estoqueapi.repository;

import br.com.senac.estoqueapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByUsuarioStatusNot(Integer status);

    Optional<Usuario> findByUsuarioIdAndUsuarioStatusNot(Integer usuarioId, Integer status);
}
