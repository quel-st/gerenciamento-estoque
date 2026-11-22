package com.Projeto.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Projeto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Buscar categoria pelo nome
    List<Categoria> findByNomeCategoriaContainingIgnoreCase(String nome);

    // Buscar categoria pelo nome exato
    Optional<Categoria> findByNomeCategoriaIgnoreCase(String nome);

    // Verificar se a categoria já existe
    boolean existsByNomeCategoriaIgnoreCase(String nome);

    // Listar categorias em ordem alfabética
    List<Categoria> findAllByOrderByNomeCategoriaAsc();


}
