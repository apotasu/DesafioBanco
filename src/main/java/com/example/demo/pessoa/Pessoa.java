package com.example.demo.pessoa;

import com.example.demo.conta.Conta;
import jakarta.persistence.*;
import java.util.Random;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private PessoaEnum tipo;

    private String documento;

    private int score;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Conta conta;

    public Pessoa() {
        this.score = new Random().nextInt(10);
    }
    public Pessoa(String nome, String documento, Long id){
        this.nome = nome;
        this.documento = documento;
        this.id = id;
        if(documento.length()==11){
            this.tipo=PessoaEnum.PF;
        } else {
            this.tipo=PessoaEnum.PJ;
        }
        this.score = new Random().nextInt(10);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PessoaEnum getTipo() {
        return tipo;
    }

    public void setTipo(PessoaEnum tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
