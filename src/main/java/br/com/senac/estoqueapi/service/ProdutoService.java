package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.ProdutoRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Produto;
import br.com.senac.estoqueapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findByProdutoStatusNot(STATUS_APAGADO);
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findByProdutoIdAndProdutoStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
    }

    @Transactional
    public Produto criar(ProdutoRequest request) {
        Produto produto = new Produto();
        aplicarDados(produto, request);
        if (produto.getProdutoQuantidade() == null) {
            produto.setProdutoQuantidade(0);
        }
        if (produto.getProdutoStatus() == null) {
            produto.setProdutoStatus(STATUS_ATIVO);
        }
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Integer id, ProdutoRequest request) {
        Produto produto = buscarPorId(id);
        aplicarDados(produto, request);
        return produtoRepository.save(produto);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        Produto produto = buscarPorId(id);
        produto.setProdutoStatus(STATUS_APAGADO);
        produtoRepository.save(produto);
    }

    @Transactional
    public Produto ativar(Integer id) {
        Produto produto = buscarPorId(id);
        produto.setProdutoStatus(STATUS_ATIVO);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto inativar(Integer id) {
        Produto produto = buscarPorId(id);
        produto.setProdutoStatus(STATUS_INATIVO);
        return produtoRepository.save(produto);
    }

    private void aplicarDados(Produto produto, ProdutoRequest request) {
        produto.setProdutoNome(request.produtoNome());
        if (request.produtoQuantidade() != null) {
            produto.setProdutoQuantidade(request.produtoQuantidade());
        }
        produto.setProdutoPreco(request.produtoPreco());
        produto.setProdutoCodigo(request.produtoCodigo());
        produto.setProdutoDataValidade(request.produtoDataValidade());
        if (request.produtoStatus() != null) {
            produto.setProdutoStatus(request.produtoStatus());
        }
    }
}
