package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.VendaRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Usuario;
import br.com.senac.estoqueapi.model.Venda;
import br.com.senac.estoqueapi.repository.UsuarioRepository;
import br.com.senac.estoqueapi.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final VendaRepository vendaRepository;
    private final UsuarioRepository usuarioRepository;

    public VendaService(VendaRepository vendaRepository, UsuarioRepository usuarioRepository) {
        this.vendaRepository = vendaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Venda> listar() {
        return vendaRepository.findByVendaStatusNot(STATUS_APAGADO);
    }

    public Venda buscarPorId(Integer id) {
        return vendaRepository.findByVendaIdAndVendaStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada."));
    }

    @Transactional
    public Venda criar(VendaRequest request) {
        Venda venda = new Venda();
        aplicarDados(venda, request);
        if (venda.getVendaData() == null) {
            venda.setVendaData(LocalDateTime.now());
        }
        if (venda.getVendaStatus() == null) {
            venda.setVendaStatus(STATUS_ATIVO);
        }
        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda atualizar(Integer id, VendaRequest request) {
        Venda venda = buscarPorId(id);
        aplicarDados(venda, request);
        return vendaRepository.save(venda);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        Venda venda = buscarPorId(id);
        venda.setVendaStatus(STATUS_APAGADO);
        vendaRepository.save(venda);
    }

    @Transactional
    public Venda ativar(Integer id) {
        Venda venda = buscarPorId(id);
        venda.setVendaStatus(STATUS_ATIVO);
        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda inativar(Integer id) {
        Venda venda = buscarPorId(id);
        venda.setVendaStatus(STATUS_INATIVO);
        return vendaRepository.save(venda);
    }

    private void aplicarDados(Venda venda, VendaRequest request) {
        Usuario usuario = usuarioRepository.findByUsuarioIdAndUsuarioStatusNot(request.usuarioId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário informado na venda não encontrado."));

        if (request.vendaData() != null) {
            venda.setVendaData(request.vendaData());
        }
        venda.setVendaValorTotal(request.vendaValorTotal());
        venda.setUsuario(usuario);

        if (request.vendaStatus() != null) {
            venda.setVendaStatus(request.vendaStatus());
        }
    }
}
