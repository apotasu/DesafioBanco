package com.example.demo.pessoa;

import com.example.demo.conta.Conta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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

    @OneToOne
    @JoinColumn(name = "pessoa_id")
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
    public Pessoa(String nome, String documento){
        this.nome = nome;
        this.documento = documento;
        if(documento.length()==11){
            this.tipo=PessoaEnum.PF;
        } else {
            this.tipo=PessoaEnum.PJ;
        }
        this.score = new Random().nextInt(10);
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

    public void updateTipo(String documento) {
        if(documento.length()==11){
            this.tipo=PessoaEnum.PF;
        } else {
            this.tipo=PessoaEnum.PJ;
        }
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

    public int getScore() {
        return score;
    }
}
