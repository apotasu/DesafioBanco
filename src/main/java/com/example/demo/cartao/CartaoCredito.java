package com.example.demo.cartao;

import com.example.demo.conta.Conta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double limite;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public CartaoCredito() {
    }

    public CartaoCredito(double limite) {
        this.limite = limite;
    }

    public CartaoCredito(double limite, Conta conta) {
        this.limite = limite;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public CartaoCredito updateLimiteCartaoCredito(int score){
        CartaoCredito cartaoCredito = new CartaoCredito();
        if (score >= 2 && score <= 5) {
            cartaoCredito =new CartaoCredito(200.0);
        } else if (score >= 6 && score <= 8) {
            cartaoCredito = new CartaoCredito(2000.0);
        } else if (score == 9) {
            cartaoCredito = new CartaoCredito(15000.0);
        }
        return cartaoCredito;
    }
}
