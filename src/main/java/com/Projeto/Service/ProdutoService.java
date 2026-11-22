package com.Projeto.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> criar(String nome, BigDecimal preco, Integer qtd) {
        validar(nome, preco, qtd);

        return jdbcTemplate.queryForMap(
                "insert into produto (nome, preco, qtd) values (?, ?, ?) "
                        + "returning id, nome, preco, qtd",
                nome.trim(), preco, qtd);
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
