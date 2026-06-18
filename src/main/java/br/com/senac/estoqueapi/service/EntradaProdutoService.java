package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.EntradaProdutoRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Entrada;
import br.com.senac.estoqueapi.model.EntradaProduto;
import br.com.senac.estoqueapi.model.Produto;
import br.com.senac.estoqueapi.repository.EntradaProdutoRepository;
import br.com.senac.estoqueapi.repository.EntradaRepository;
import br.com.senac.estoqueapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntradaProdutoService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final EntradaProdutoRepository entradaProdutoRepository;
    private final EntradaRepository entradaRepository;
    private final ProdutoRepository produtoRepository;

    public EntradaProdutoService(EntradaProdutoRepository entradaProdutoRepository,
                                 EntradaRepository entradaRepository,
                                 ProdutoRepository produtoRepository) {
        this.entradaProdutoRepository = entradaProdutoRepository;
        this.entradaRepository = entradaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<EntradaProduto> listar() {
        return entradaProdutoRepository.findByEntradaProdutoStatusNot(STATUS_APAGADO);
    }

    public EntradaProduto buscarPorId(Integer id) {
        return entradaProdutoRepository.findByEntradaProdutoIdAndEntradaProdutoStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Item de entrada não encontrado."));
    }

    @Transactional
    public EntradaProduto criar(EntradaProdutoRequest request) {
        EntradaProduto entradaProduto = new EntradaProduto();
        aplicarDados(entradaProduto, request);
        if (entradaProduto.getEntradaProdutoStatus() == null) {
            entradaProduto.setEntradaProdutoStatus(STATUS_ATIVO);
        }
        return entradaProdutoRepository.save(entradaProduto);
    }

    @Transactional
    public EntradaProduto atualizar(Integer id, EntradaProdutoRequest request) {
        EntradaProduto entradaProduto = buscarPorId(id);
        aplicarDados(entradaProduto, request);
        return entradaProdutoRepository.save(entradaProduto);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        EntradaProduto entradaProduto = buscarPorId(id);
        entradaProduto.setEntradaProdutoStatus(STATUS_APAGADO);
        entradaProdutoRepository.save(entradaProduto);
    }

    @Transactional
    public EntradaProduto ativar(Integer id) {
        EntradaProduto entradaProduto = buscarPorId(id);
        entradaProduto.setEntradaProdutoStatus(STATUS_ATIVO);
        return entradaProdutoRepository.save(entradaProduto);
    }

    @Transactional
    public EntradaProduto inativar(Integer id) {
        EntradaProduto entradaProduto = buscarPorId(id);
        entradaProduto.setEntradaProdutoStatus(STATUS_INATIVO);
        return entradaProdutoRepository.save(entradaProduto);
    }

    private void aplicarDados(EntradaProduto entradaProduto, EntradaProdutoRequest request) {
        Entrada entrada = entradaRepository.findByEntradaIdAndEntradaStatusNot(request.entradaId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Entrada informada não encontrada."));

        Produto produto = produtoRepository.findByProdutoIdAndProdutoStatusNot(request.produtoId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Produto informado não encontrado."));

        entradaProduto.setEntrada(entrada);
        entradaProduto.setProduto(produto);
        entradaProduto.setQuantidade(request.quantidade());
        entradaProduto.setPrecoCompra(request.precoCompra());

        if (request.entradaProdutoStatus() != null) {
            entradaProduto.setEntradaProdutoStatus(request.entradaProdutoStatus());
        }
    }
}
