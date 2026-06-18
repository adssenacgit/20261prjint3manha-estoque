package br.com.senac.estoqueapi.controller;

import br.com.senac.estoqueapi.dto.VendaRequest;
import br.com.senac.estoqueapi.model.Venda;
import br.com.senac.estoqueapi.service.VendaService;
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
@RequestMapping("/api/vendas")
@CrossOrigin("*")
@Tag(name = "Vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    @Operation(summary = "Lista vendas não apagadas")
    public ResponseEntity<List<Venda>> listar() {
        return ResponseEntity.ok(vendaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma venda pelo ID")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma venda")
    public ResponseEntity<Venda> criar(@Valid @RequestBody VendaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma venda")
    public ResponseEntity<Venda> atualizar(@PathVariable Integer id, @Valid @RequestBody VendaRequest request) {
        return ResponseEntity.ok(vendaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente uma venda, alterando status para -1")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        vendaService.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Ativa uma venda, alterando status para 1")
    public ResponseEntity<Venda> ativar(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaService.ativar(id));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativa uma venda, alterando status para 0")
    public ResponseEntity<Venda> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(vendaService.inativar(id));
    }
}
