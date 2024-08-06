package com.example.demo.conta.controller;

import com.example.demo.conta.entity.Conta;
import com.example.demo.conta.service.ContaService;
import com.example.demo.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ContaControllerTest {
    @Mock
    ContaService contaService;

    @InjectMocks
    ContaController contaController;

        @Test
        void shouldCreateAccount_WhenValidPessoaProvided() {
            Pessoa pessoa = new Pessoa("Mario","12345678901");
            Conta conta = new Conta("0324", pessoa.getDocumento(),pessoa);

            contaController.createAccount(pessoa.getId(), conta.getAgencia());

            assertEquals(1,contaController.getContas().size(), "Deve ter criado a conta");
        }
        @Test
        void shouldNotAddPessoa_WhenInvalidPessoaProvided() {
            Pessoa pessoa = new Pessoa("Mario","1234568901");
            Conta conta = new Conta("0324", pessoa.getDocumento(),pessoa);

            contaController.createAccount(pessoa.getId(), conta.getAgencia());

            assertEquals(0,contaController.getContas().size(), "Não deve ter criado a conta");
        }
    }

