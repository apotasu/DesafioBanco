package com.example.demo.pessoa.controller;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.service.PessoaService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.Assert;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {
    @Mock
    PessoaService pessoaService;

    @InjectMocks
    PessoaController pessoaController;

    @Nested
    class getContas{
        @Test
        void shouldReturnHttpOk() {
            //ARRANGE - Prepara para a execucação os mocks
            Pessoa pessoa = new Pessoa("Mario", "86186704321");

            doReturn(null).when(pessoaService).addNewPessoa(pessoa);
            //ACT - Executar metodo a ser testado
            //var response = pessoaController.addNewPessoa(pessoa);
            //ASSERT - Verifica se a execução foi correta

            //HttpResponse httpResp = ;
            //int code = httpResp.getStatusLine().getStatusCode();

            //assertEquals(HttpStatusCode.valueOf(200), );
        }
        @Test
        void shouldPassCorrectParameterToService() {

        }
        @Test
        void shouldReturnResponeBodyCorrectly() {

        }
    }

}