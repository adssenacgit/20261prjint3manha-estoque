package br.com.senac.estoqueapi.controller;

import br.com.senac.estoqueapi.dto.ProdutoRequest;
import br.com.senac.estoqueapi.model.Produto;
import br.com.senac.estoqueapi.service.ProdutoService;
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
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
@Tag(name = "Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @Operation(summary = "Lista produtos não apagados")
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto pelo ID")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um produto")
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente um produto, alterando status para -1")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        produtoService.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Ativa um produto, alterando status para 1")
    public ResponseEntity<Produto> ativar(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.ativar(id));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativa um produto, alterando status para 0")
    public ResponseEntity<Produto> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.inativar(id));
    }
}
