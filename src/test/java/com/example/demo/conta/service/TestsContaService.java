package com.example.demo.conta.service;

import com.example.demo.cartao.entity.CartaoCredito;
import com.example.demo.cheque.entity.ChequeEspecial;
import com.example.demo.conta.entity.Conta;
import com.example.demo.conta.repository.ContaRepository;
import com.example.demo.pessoa.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestsContaService {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        contaService = new ContaService();
    }

    @Test
    void testCreateAccount_WhenValidPessoaProvided() {
        Pessoa pessoa = new Pessoa("Peach", "12345678901");
        Conta conta = new Conta();
        conta.setPessoa(pessoa);

        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        //A conta nunca é criada?
        contaService.createAccount(pessoa, "0001");

        assertEquals(1, contaService.getContas().size(), "A conta deve ser criada");
    }

    @Test
    void testCreateAccount_WhenNoPessoaProvided() {
        List<Conta> retrievedConta = new ArrayList<>();
        when(contaRepository.findAll()).thenReturn(retrievedConta);

        List<Conta> contas = contaService.getContas();
        Assert.isTrue(contas.isEmpty(), "Repository deve estar vazio");
    }

    @Test
    void testDeleteAccount_WhenValidAccountIdProvided() {
        Pessoa pessoa = new Pessoa();
        Conta conta = new Conta();
        conta.setPessoa(pessoa);

        when(contaRepository.findContaById(1L)).thenReturn(java.util.Optional.of(conta));

        contaService.deleteAccount(1L);

        verify(contaRepository).deleteById(1L);
    }

    @Test
    void testDeleteAccount_WhenInvalidAccountIdProvided() {
        when(contaRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        contaService.deleteAccount(1L);

        verify(contaRepository, never()).deleteById(1L);
    }


    @Test
    void testAccountCreationWithScoreBetween2And5() {
        Pessoa pessoa = new Pessoa();
        pessoa.setScore(3);
        contaService.createAccount(pessoa, "0001");
        Conta conta = contaService.getContas().get(0);

        Assert.isTrue(conta.getPessoa().getScore() >= 2 && conta.getPessoa().getScore() <= 5,
                "Score da pessoa deve estar entre 2 e 5");

        CartaoCredito cartaoCredito = conta.getCartaoCredito();
        ChequeEspecial chequeEspecial = conta.getChequeEspecial();

        Assert.isTrue(cartaoCredito.getLimite() == 200, "O limite do cartão de crédito deve ser 200");
        Assert.isTrue(chequeEspecial.getLimite() == 1000, "O limite do cheque especial deve ser 1000");
    }

    @Test
    void testAccountCreationWithScoreBetween6And8() {
        Pessoa pessoa = new Pessoa();
        pessoa.setScore(7);
        contaService.createAccount(pessoa, "0001");
        Conta conta = contaService.getContas().get(0);

        Assert.isTrue(conta.getPessoa().getScore() >= 6 && conta.getPessoa().getScore() <= 8,
                "Score da pessoa deve estar entre 6 e 8");

        CartaoCredito cartaoCredito = conta.getCartaoCredito();
        ChequeEspecial chequeEspecial = conta.getChequeEspecial();

        Assert.isTrue(cartaoCredito.getLimite() == 2000, "O limite do cartão de crédito deve ser 2000");
        Assert.isTrue(chequeEspecial.getLimite() == 2000, "O limite do cheque especial deve ser 2000");
    }

    @Test
    void testAccountCreationWithScoreEqualTo9() {
        Pessoa pessoa = new Pessoa();
        pessoa.setScore(9);
        contaService.createAccount(pessoa, "0001");
        Conta conta = contaService.getContas().get(0);

        Assert.isTrue(conta.getPessoa().getScore() == 9, "Score da pessoa deve ser igual a 9");

        CartaoCredito cartaoCredito = conta.getCartaoCredito();
        ChequeEspecial chequeEspecial = conta.getChequeEspecial();

        Assert.isTrue(cartaoCredito.getLimite() == 15000, "O limite do cartão de crédito deve ser 15000");
        Assert.isTrue(chequeEspecial.getLimite() == 5000, "O limite do cheque especial deve ser 5000");
    }
}
