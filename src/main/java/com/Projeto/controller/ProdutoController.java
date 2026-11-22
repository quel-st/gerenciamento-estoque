package com.projeto.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@RequestBody ProdutoRequest request) {
        Map<String, Object> produto = produtoService.criar(
                request.nome(), request.preco(), request.qtd());
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarDadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("erro", exception.getMessage()));
    }

    public record ProdutoRequest(String nome, BigDecimal preco, Integer qtd) {
    }
}
