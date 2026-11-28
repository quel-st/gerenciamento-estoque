package com.projeto.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Map<String, Object>> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Map<String, Object> buscar(@PathVariable UUID id) {
        return produtoService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@RequestBody ProdutoRequest request) {
        Map<String, Object> produto = produtoService.criar(
                request.nome(), request.preco(), request.qtd());
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public Map<String, Object> atualizar(
            @PathVariable UUID id,
            @RequestBody ProdutoRequest request) {
        return produtoService.atualizar(id, request.nome(), request.preco(), request.qtd());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarDadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("erro", exception.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> tratarProdutoNaoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }

    public record ProdutoRequest(String nome, BigDecimal preco, Integer qtd) {
    }
}
