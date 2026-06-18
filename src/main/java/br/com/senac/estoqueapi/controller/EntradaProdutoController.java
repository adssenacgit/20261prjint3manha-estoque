package br.com.senac.estoqueapi.controller;

import br.com.senac.estoqueapi.dto.EntradaProdutoRequest;
import br.com.senac.estoqueapi.model.EntradaProduto;
import br.com.senac.estoqueapi.service.EntradaProdutoService;
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
@RequestMapping("/api/entradas-produtos")
@CrossOrigin("*")
@Tag(name = "Entrada Produtos")
public class EntradaProdutoController {

    private final EntradaProdutoService entradaProdutoService;

    public EntradaProdutoController(EntradaProdutoService entradaProdutoService) {
        this.entradaProdutoService = entradaProdutoService;
    }

    @GetMapping
    @Operation(summary = "Lista itens de entrada não apagados")
    public ResponseEntity<List<EntradaProduto>> listar() {
        return ResponseEntity.ok(entradaProdutoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um item de entrada pelo ID")
    public ResponseEntity<EntradaProduto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaProdutoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um item de entrada")
    public ResponseEntity<EntradaProduto> criar(@Valid @RequestBody EntradaProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entradaProdutoService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um item de entrada")
    public ResponseEntity<EntradaProduto> atualizar(@PathVariable Integer id, @Valid @RequestBody EntradaProdutoRequest request) {
        return ResponseEntity.ok(entradaProdutoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente um item de entrada, alterando status para -1")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        entradaProdutoService.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Ativa um item de entrada, alterando status para 1")
    public ResponseEntity<EntradaProduto> ativar(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaProdutoService.ativar(id));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativa um item de entrada, alterando status para 0")
    public ResponseEntity<EntradaProduto> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaProdutoService.inativar(id));
    }
}
