package br.com.senac.estoqueapi.controller;

import br.com.senac.estoqueapi.dto.VendaProdutoRequest;
import br.com.senac.estoqueapi.model.VendaProduto;
import br.com.senac.estoqueapi.service.VendaProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendas-produtos")
@CrossOrigin("*")
@Tag(name = "Venda Produtos")
public class VendaProdutoController {

    private final VendaProdutoService vendaProdutoService;

    public VendaProdutoController(VendaProdutoService vendaProdutoService) {
        this.vendaProdutoService = vendaProdutoService;
    }

    @GetMapping
    @Operation(summary = "Lista itens de venda não apagados")
    public ResponseEntity<List<VendaProduto>> listar() {
        return ResponseEntity.ok(vendaProdutoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um item de venda pelo ID")
    public ResponseEntity<VendaProduto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaProdutoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um item de venda")
    public ResponseEntity<VendaProduto> criar(@Valid @RequestBody VendaProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaProdutoService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um item de venda")
    public ResponseEntity<VendaProduto> atualizar(@PathVariable Integer id, @Valid @RequestBody VendaProdutoRequest request) {
        return ResponseEntity.ok(vendaProdutoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente um item de venda, alterando status para -1")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        vendaProdutoService.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Ativa um item de venda, alterando status para 1")
    public ResponseEntity<VendaProduto> ativar(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaProdutoService.ativar(id));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativa um item de venda, alterando status para 0")
    public ResponseEntity<VendaProduto> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaProdutoService.inativar(id));
    }
}
