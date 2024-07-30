package com.example.demo.pessoa.service;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
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
    void shouldReturnHttpOk_WhenPessoaIsAddedSuccessfully() {
        Pessoa pessoa = new Pessoa("Maria", "94510283040");
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        ResponseEntity<Void> response = pessoaService.addNewPessoa(pessoa);
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
    void shouldReturnEmpty_WhenInvalidValuesProvided() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.save(pessoa)).thenReturn(null);
        when(pessoaRepository.findAll()).thenReturn(Collections.emptyList());
        pessoaService.addNewPessoa(pessoa);
        var pessoas = pessoaService.getPessoa();
        assertTrue(pessoas.isEmpty(), "Repository deve estar vazio");
    }

    @Test
    void shouldDeletePessoa_WhenValidIdProvided() {
        Pessoa pessoa = new Pessoa("Chris", "12345678901234");
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        pessoaService.addNewPessoa(pessoa);
        pessoaService.deletePessoa(1L);
        verify(pessoaRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldNotFindPessoa_WhenInvalidIdProvidedAfterDeletion() {
        Pessoa pessoa = new Pessoa("Chris", "12345678901234");
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.findById(999L)).thenReturn(Optional.empty());
        pessoaService.addNewPessoa(pessoa);
        pessoaService.deletePessoa(1L);
        Optional<Pessoa> retrievedPessoa = Optional.ofNullable(pessoaService.getPessoaById(999L));
        assertTrue(retrievedPessoa.isEmpty(), "Pessoa não deve estar presente após deleção");
    }
}
