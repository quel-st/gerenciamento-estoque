package com.projeto.repository;
import com.projeto.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produto pelo nome
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar produtos com quantidade abaixo do limite
    List<Produto> findByQtdLessThanEqual(Integer quantidade);
    
} 