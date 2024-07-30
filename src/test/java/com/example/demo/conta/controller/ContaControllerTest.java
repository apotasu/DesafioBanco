package com.example.demo.conta.controller;

import com.example.demo.conta.service.ContaService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContaControllerTest {
    @Mock
    ContaService contaService;

    @InjectMocks
    ContaController contaController;

    @Nested
    class getContas{
        @Test
        void shouldReturnHttpOk() {
            //ARRANGE - Prepara para a execucação os mocks

            //ACT - Executar metodo a ser testado
            contaController.getContas();
            //ASSERT - Verifica se a execução foi correta

        }
        @Test
        void shouldPassCorrectParameterToService() {

        }
        @Test
        void shouldReturnResponeBodyCorrectly() {

        }
    }

}