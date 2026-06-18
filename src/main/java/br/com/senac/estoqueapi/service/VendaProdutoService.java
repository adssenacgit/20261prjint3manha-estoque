package br.com.senac.estoqueapi.service;

import br.com.senac.estoqueapi.dto.VendaProdutoRequest;
import br.com.senac.estoqueapi.exception.ResourceNotFoundException;
import br.com.senac.estoqueapi.model.Produto;
import br.com.senac.estoqueapi.model.Venda;
import br.com.senac.estoqueapi.model.VendaProduto;
import br.com.senac.estoqueapi.repository.ProdutoRepository;
import br.com.senac.estoqueapi.repository.VendaProdutoRepository;
import br.com.senac.estoqueapi.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendaProdutoService {

    private static final int STATUS_APAGADO = -1;
    private static final int STATUS_INATIVO = 0;
    private static final int STATUS_ATIVO = 1;

    private final VendaProdutoRepository vendaProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;

    public VendaProdutoService(VendaProdutoRepository vendaProdutoRepository,
                               ProdutoRepository produtoRepository,
                               VendaRepository vendaRepository) {
        this.vendaProdutoRepository = vendaProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<VendaProduto> listar() {
        return vendaProdutoRepository.findByVendaProdutoStatusNot(STATUS_APAGADO);
    }

    public VendaProduto buscarPorId(Integer id) {
        return vendaProdutoRepository.findByVendaProdutoIdAndVendaProdutoStatusNot(id, STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Item de venda não encontrado."));
    }

    @Transactional
    public VendaProduto criar(VendaProdutoRequest request) {
        VendaProduto vendaProduto = new VendaProduto();
        aplicarDados(vendaProduto, request);
        if (vendaProduto.getVendaProdutoStatus() == null) {
            vendaProduto.setVendaProdutoStatus(STATUS_ATIVO);
        }
        return vendaProdutoRepository.save(vendaProduto);
    }

    @Transactional
    public VendaProduto atualizar(Integer id, VendaProdutoRequest request) {
        VendaProduto vendaProduto = buscarPorId(id);
        aplicarDados(vendaProduto, request);
        return vendaProdutoRepository.save(vendaProduto);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        VendaProduto vendaProduto = buscarPorId(id);
        vendaProduto.setVendaProdutoStatus(STATUS_APAGADO);
        vendaProdutoRepository.save(vendaProduto);
    }

    @Transactional
    public VendaProduto ativar(Integer id) {
        VendaProduto vendaProduto = buscarPorId(id);
        vendaProduto.setVendaProdutoStatus(STATUS_ATIVO);
        return vendaProdutoRepository.save(vendaProduto);
    }

    @Transactional
    public VendaProduto inativar(Integer id) {
        VendaProduto vendaProduto = buscarPorId(id);
        vendaProduto.setVendaProdutoStatus(STATUS_INATIVO);
        return vendaProdutoRepository.save(vendaProduto);
    }

    private void aplicarDados(VendaProduto vendaProduto, VendaProdutoRequest request) {
        Produto produto = produtoRepository.findByProdutoIdAndProdutoStatusNot(request.produtoId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Produto informado não encontrado."));

        Venda venda = vendaRepository.findByVendaIdAndVendaStatusNot(request.vendaId(), STATUS_APAGADO)
                .orElseThrow(() -> new ResourceNotFoundException("Venda informada não encontrada."));

        vendaProduto.setProduto(produto);
        vendaProduto.setVendaQuantidade(request.vendaQuantidade());
        vendaProduto.setVendaPrecoUnidade(request.vendaPrecoUnidade());
        vendaProduto.setVenda(venda);

        if (request.vendaProdutoStatus() != null) {
            vendaProduto.setVendaProdutoStatus(request.vendaProdutoStatus());
        }
    }
}
