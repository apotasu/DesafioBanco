package com.example.demo.cheque;

import com.example.demo.cartao.CartaoCredito;
import com.example.demo.conta.Conta;
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

    public Long getId() {
        return id;
    }

    public double getLimite() {
        return limite;
    }

    public Conta getConta() {
        return conta;
    }

    public ChequeEspecial updateLimiteCheque(int score){
        ChequeEspecial chequeEspecial = new ChequeEspecial();
        if (score >= 2 && score <= 5) {
           chequeEspecial = new ChequeEspecial(1000.0);

        } else if (score >= 6 && score <= 8) {
            chequeEspecial = new ChequeEspecial(2000.0);

        } else if (score == 9) {
            chequeEspecial = new ChequeEspecial(5000.0);
        }
        return chequeEspecial;
    }

}
