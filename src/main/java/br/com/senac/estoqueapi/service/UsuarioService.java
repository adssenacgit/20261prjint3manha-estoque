package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.UsuarioRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Usuario;
import br.com.senac.estoqueapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findByUsuarioStatusNot(STATUS_APAGADO);
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findByUsuarioIdAndUsuarioStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        aplicarDados(usuario, request);
        if (usuario.getUsuarioStatus() == null) {
            usuario.setUsuarioStatus(STATUS_ATIVO);
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Integer id, UsuarioRequest request) {
        Usuario usuario = buscarPorId(id);
        aplicarDados(usuario, request);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuario.setUsuarioStatus(STATUS_APAGADO);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario ativar(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuario.setUsuarioStatus(STATUS_ATIVO);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario inativar(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuario.setUsuarioStatus(STATUS_INATIVO);
        return usuarioRepository.save(usuario);
    }

    private void aplicarDados(Usuario usuario, UsuarioRequest request) {
        usuario.setUsuarioNome(request.usuarioNome());
        usuario.setEmpresaNome(request.empresaNome());
        usuario.setUsuarioEmail(request.usuarioEmail());
        usuario.setUsuarioCpf(request.usuarioCpf());
        usuario.setUsuarioSenha(request.usuarioSenha());
        if (request.usuarioStatus() != null) {
            usuario.setUsuarioStatus(request.usuarioStatus());
        }
    }
}
