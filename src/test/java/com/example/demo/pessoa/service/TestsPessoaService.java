package com.example.demo.pessoa.service;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class TestsPessoaService {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldAddAndGetPessoa_WhenValidValuesProvided() {
        Pessoa pessoa = new Pessoa("Julia", "12345678901");
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));
        pessoaService.addNewPessoa(pessoa);
        var pessoas = pessoaService.getPessoa();
        assertFalse(pessoas.isEmpty(), "Repository não deve estar vazio");
    }

    @Test
    void shouldGetPessoaById_WhenValidIdProvided() {
        Pessoa pessoa = new Pessoa("Chris", "12345678901234");
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        pessoaService.addNewPessoa(pessoa);
        Optional<Pessoa> retrievedPessoa = Optional.ofNullable(pessoaService.getPessoaById(1L));
        assertTrue(retrievedPessoa.isPresent(), "Pessoa deve estar presente");
    }

    @Test
    void shouldDeletePessoa_WhenValidIdProvided() {
        Pessoa pessoa = new Pessoa("Chris", "12345678901234");
        pessoaService.addNewPessoa(pessoa);
        pessoaService.deletePessoa(1L);
        assertEquals(0, pessoaService.getPessoa().size());
    }

    @Test
    void shouldNotFindPessoa_WhenInvalidIdProvidedAfterDeletion() {
        Pessoa pessoa = new Pessoa("Chris", "12345678901234");
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.findById(999L)).thenReturn(Optional.empty());
        //Pessoa nunca é salva no repository
        //why
        pessoaService.addNewPessoa(pessoa);
        pessoaService.deletePessoa(1L);
        Optional<Pessoa> retrievedPessoa = Optional.ofNullable(pessoaService.getPessoaById(999L));
        assertTrue(retrievedPessoa.isEmpty(), "Pessoa não deve estar presente após deleção");
    }
}
