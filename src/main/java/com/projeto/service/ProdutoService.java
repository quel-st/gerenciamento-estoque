package com.projeto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> listar() {
        return jdbcTemplate.queryForList(
                "select id, nome, preco, qtd from produto order by id");
    }

    public Map<String, Object> buscar(UUID id) {
        try {
            return jdbcTemplate.queryForMap(
                    "select id, nome, preco, qtd from produto where id = ?", id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NoSuchElementException("Produto não encontrado.");
        }
    }

    public Map<String, Object> criar(String nome, BigDecimal preco, Integer qtd) {
        validar(nome, preco, qtd);

        UUID id = UUID.randomUUID();
        return jdbcTemplate.queryForMap(
                "insert into produto (id, nome, preco, qtd) values (?, ?, ?, ?) "
                        + "returning id, nome, preco, qtd",
                id, nome.trim(), preco, qtd);
    }

    public Map<String, Object> atualizar(UUID id, String nome, BigDecimal preco, Integer qtd) {
        validar(nome, preco, qtd);

        int produtosAtualizados = jdbcTemplate.update(
                "update produto set nome = ?, preco = ?, qtd = ? where id = ?",
                nome.trim(), preco, qtd, id);
        if (produtosAtualizados == 0) {
            throw new NoSuchElementException("Produto não encontrado.");
        }

        return buscar(id);
    }

    public void excluir(UUID id) {
        int produtosExcluidos = jdbcTemplate.update(
                "delete from produto where id = ?", id);
        if (produtosExcluidos == 0) {
            throw new NoSuchElementException("Produto não encontrado.");
        }
    }

    private void validar(String nome, BigDecimal preco, Integer qtd) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (preco == null || preco.signum() < 0) {
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }
        if (qtd == null || qtd < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero.");
        }
    }
}
