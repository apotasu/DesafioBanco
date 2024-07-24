package com.example.demo.conta;

import com.example.demo.cartao.CartaoCredito;
import com.example.demo.cheque.ChequeEspecial;
import com.example.demo.pessoa.Pessoa;
import com.example.demo.pessoa.PessoaEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Random;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String agencia;

    private TipoEnum tipoConta;

    private String documento;

    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
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

    public Conta(String agencia, String documento) {
        this.agencia = agencia;
        this.numero = String.format("%06d", new Random().nextInt(1000000));
    }

    public Conta(String agencia, String documento, Pessoa pessoa) {
        this.agencia = agencia;
        this.numero = String.format("%06d", new Random().nextInt(1000000));
        this.pessoa = pessoa;
    }


    public void setTipoConta(PessoaEnum tipo) {
        if(tipo == PessoaEnum.PF){
            this.tipoConta = TipoEnum.CORRENTE;
        } else {
            this.tipoConta = TipoEnum.EMPRESARIAL;
        }
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setLimites(ChequeEspecial chequeEspecial, CartaoCredito cartaoCredito){
        this.chequeEspecial = chequeEspecial;
        this.cartaoCredito = cartaoCredito;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                ", tipoConta=" + tipoConta +
                ", documento='" + documento + '\'' +
                ", chequeEspecial=" + chequeEspecial +
                ", cartaoCredito=" + cartaoCredito +
                '}';
    }
}
