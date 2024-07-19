package com.example.demo.cheque;

import com.example.demo.conta.Conta;
import jakarta.persistence.*;

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

    public ChequeEspecial(double limite, Conta conta) {
        this.limite = limite;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public double getLimite() {
        return limite;
    }

    public Conta getConta() {
        return conta;
    }
}
