package com.projeto.repository;

import com.projeto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Buscar categoria pelo nome
    // Adicionar somente se essa funcionalidade for necessária
}
