package com.example.demo.cartao;

import com.example.demo.conta.Conta;
import jakarta.persistence.*;

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
}
