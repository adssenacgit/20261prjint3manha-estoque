package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.EntradaRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Entrada;
import br.com.senac.estoqueapi.model.Usuario;
import br.com.senac.estoqueapi.repository.EntradaRepository;
import br.com.senac.estoqueapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntradaService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final EntradaRepository entradaRepository;
    private final UsuarioRepository usuarioRepository;

    public EntradaService(EntradaRepository entradaRepository, UsuarioRepository usuarioRepository) {
        this.entradaRepository = entradaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Entrada> listar() {
        return entradaRepository.findByEntradaStatusNot(STATUS_APAGADO);
    }

    public Entrada buscarPorId(Integer id) {
        return entradaRepository.findByEntradaIdAndEntradaStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Entrada não encontrada."));
    }

    @Transactional
    public Entrada criar(EntradaRequest request) {
        Entrada entrada = new Entrada();
        aplicarDados(entrada, request);
        if (entrada.getEntradaData() == null) {
            entrada.setEntradaData(LocalDateTime.now());
        }
        if (entrada.getEntradaStatus() == null) {
            entrada.setEntradaStatus(STATUS_ATIVO);
        }
        return entradaRepository.save(entrada);
    }

    @Transactional
    public Entrada atualizar(Integer id, EntradaRequest request) {
        Entrada entrada = buscarPorId(id);
        aplicarDados(entrada, request);
        return entradaRepository.save(entrada);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        Entrada entrada = buscarPorId(id);
        entrada.setEntradaStatus(STATUS_APAGADO);
        entradaRepository.save(entrada);
    }

    @Transactional
    public Entrada ativar(Integer id) {
        Entrada entrada = buscarPorId(id);
        entrada.setEntradaStatus(STATUS_ATIVO);
        return entradaRepository.save(entrada);
    }

    @Transactional
    public Entrada inativar(Integer id) {
        Entrada entrada = buscarPorId(id);
        entrada.setEntradaStatus(STATUS_INATIVO);
        return entradaRepository.save(entrada);
    }

    private void aplicarDados(Entrada entrada, EntradaRequest request) {
        Usuario usuario = usuarioRepository.findByUsuarioIdAndUsuarioStatusNot(request.usuarioId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário informado na entrada não encontrado."));

        if (request.entradaData() != null) {
            entrada.setEntradaData(request.entradaData());
        }
        entrada.setEntradaFornecedor(request.entradaFornecedor());
        entrada.setEntradaValorTotal(request.entradaValorTotal());
        entrada.setUsuario(usuario);

        if (request.entradaStatus() != null) {
            entrada.setEntradaStatus(request.entradaStatus());
        }
    }
}
