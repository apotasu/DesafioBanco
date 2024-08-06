package com.example.demo.pessoa.controller;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {
    @Mock
    PessoaService pessoaService;

    @InjectMocks
    PessoaController pessoaController;

    @Test
    void shouldAddPessoa_WhenValidPessoaProvided() {
        Pessoa pessoa = new Pessoa("Mario", "86186704321");
        doReturn(true).when(pessoaService).addNewPessoa(pessoa);
        pessoaController.addNewPessoa(pessoa);
        assertEquals(1, pessoaController.getPessoa().size(), "A pessoa deve ter sido adicionada");
    }

    @Test
    void shouldNotAddPessoa_WhenInvalidPessoaProvided() {
        Pessoa pessoa = new Pessoa("Mario", "861867321");
        doReturn(false).when(pessoaService).addNewPessoa(pessoa);
        pessoaController.addNewPessoa(pessoa);
        assertEquals(0, pessoaController.getPessoa().size(), "A pessoa não deve ter sido adicionada");
    }

    @Test
    void shouldAddPessoa_WhenValidBodyProvided() {
        Pessoa pessoa = new Pessoa("Luigi", "12345678900");
        doReturn(true).when(pessoaService).addNewPessoa(pessoa);
        pessoaController.addNewPessoa(pessoa);
        assertEquals(1, pessoaController.getPessoa().size(), "A pessoa deve ter sido adicionada");
    }

    @Test
    void shouldReturnResponseBodyCorrectly() {
        Pessoa pessoa = new Pessoa("Peach", "98765432100");
        doReturn(pessoa).when(pessoaService).getPessoaById(1L);
        Pessoa response = pessoaController.getPessoaById(1L);
        assertEquals(pessoa, response, "O corpo da resposta deve estar correto");
    }
}
