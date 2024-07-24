package com.example.demo.cartao.entity;

import com.example.demo.conta.entity.Conta;
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
}
