package br.com.senac.estoqueapi.controller;

import br.com.senac.estoqueapi.dto.EntradaRequest;
import br.com.senac.estoqueapi.model.Entrada;
import br.com.senac.estoqueapi.service.EntradaService;
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
@RequestMapping("/api/entradas")
@CrossOrigin("*")
@Tag(name = "Entradas")
public class EntradaController {

    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping
    @Operation(summary = "Lista entradas não apagadas")
    public ResponseEntity<List<Entrada>> listar() {
        return ResponseEntity.ok(entradaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma entrada pelo ID")
    public ResponseEntity<Entrada> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma entrada")
    public ResponseEntity<Entrada> criar(@Valid @RequestBody EntradaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entradaService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma entrada")
    public ResponseEntity<Entrada> atualizar(@PathVariable Integer id, @Valid @RequestBody EntradaRequest request) {
        return ResponseEntity.ok(entradaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga logicamente uma entrada, alterando status para -1")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        entradaService.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Ativa uma entrada, alterando status para 1")
    public ResponseEntity<Entrada> ativar(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaService.ativar(id));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(summary = "Inativa uma entrada, alterando status para 0")
    public ResponseEntity<Entrada> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(entradaService.inativar(id));
    }
}
