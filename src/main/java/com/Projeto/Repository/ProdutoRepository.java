package com.projeto.repository;
import com.projeto.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produto pelo nome
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar produtos com quantidade abaixo do limite
    List<Produto> findByQtdLessThanEqual(Integer qtd);

      // Buscar produto pelo nome exato
    Optional<Produto> findByNomeIgnoreCase(String nome);

    // Buscar produtos com preço maior que determinado valor
    List<Produto> findByPrecoGreaterThan(Float preco);

    // Buscar produtos com preço menor que determinado valor
    List<Produto> findByPrecoLessThan(Float preco);

    // Listar produtos em ordem alfabética
    List<Produto> findAllByOrderByNomeAsc();

    // Buscar produtos por categoria
    List<Produto> findByCategoria_IdCategoria(Long idCategoria);

    // Verificar se produto já existe
    boolean existsByNomeIgnoreCase(String nome);
    
} 