package com.example.demo.conta;

import com.example.demo.cartao.CartaoCredito;
import com.example.demo.cheque.ChequeEspecial;
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

    private TipoEnum tipoConta;

    @OneToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    private Pessoa pessoa;

    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
    private ChequeEspecial chequeEspecial;

    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
    private CartaoCredito cartaoCredito;

    public Conta() {
    }

    public Conta(String agencia) {
        this.agencia = agencia;
        this.numero = String.format("%06d", new Random().nextInt(1000000));

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

    public TipoEnum getTipoConta() {
        return tipoConta;
    }
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }

    public void setTipoConta(PessoaEnum tipo) {
        if(tipo == PessoaEnum.PF){
            this.tipoConta = TipoEnum.CORRENTE;
        } else {
            this.tipoConta = TipoEnum.EMPRESARIAL;
        }
    }

}
