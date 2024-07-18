package com.example.demo.pessoa;


import jakarta.persistence.*;

@Entity
@Table
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String documento;

    public Pessoa(String nome, String documento, Long id) {
        this.nome = nome;
        this.documento = documento;
        this.id = id;
    }

    public Pessoa(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
