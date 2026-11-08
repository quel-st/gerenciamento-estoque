package com.projeto.repository;

import com.projeto.model.Categoria;
import com.projeto.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
