package com.example.demo.conta;

import com.example.demo.pessoa.Pessoa;
import com.example.demo.pessoa.PessoaEnum;
import jakarta.persistence.*;
import java.util.Random;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String agencia;

    private TipoEnum tipo;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Conta() {
    }

    public Conta(Pessoa pessoa, String agencia) {
        this.pessoa = pessoa;
        this.agencia = agencia;
        this.numero = String.format("%06d", new Random().nextInt(1000000));
        if(pessoa.getTipo()== PessoaEnum.PF){
            tipo = TipoEnum.CORRENTE;
        } else {
            tipo = TipoEnum.EMPRESARIAL;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
