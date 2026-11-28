package com.projeto.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// Informa que Produto é uma entidade do banco.
@Entity
public class Produto {


    @Id
    // GenerationType.UUID - gera um UUID automaticamente pelo Hibernate.
    @GeneratedValue(strategy = GenerationType.UUID)
    // UUID: corresponde ao identificador do Supabase.
    private UUID id;

    @Column(name = "\"nome\"")
    private String nome;

    private Float preco;

    @Column(name = "\"qtd\"")
    private Long qtd;

    // createdAt - representa a data gerada pelo banco.
    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    // @ManyToOne - permite relacionar vários produtos a uma categoria.
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}

