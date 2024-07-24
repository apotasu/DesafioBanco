package com.example.demo.cheque.entity;

import com.example.demo.conta.entity.Conta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class ChequeEspecial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double limite;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public ChequeEspecial() {
    }

    public ChequeEspecial(double limite) {
        this.limite = limite;
    }

    public ChequeEspecial(double limite, Conta conta) {
        this.limite = limite;
        this.conta = conta;
    }

}
