package com.example.demo.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class PessoaConfig {

    @Autowired
    private PessoaService pessoaService;

    @PostConstruct
    public void init() {
        Pessoa cath = new Pessoa("Catharina", "12345678900", 1L);
        Pessoa lari = new Pessoa("Larrissa", "00987654321234" ,2L);
        Pessoa pedro = new Pessoa("Pedro", "12356712345", 3L);

        pessoaService.addNewPessoa(cath, "0003");
        pessoaService.addNewPessoa(lari, "0002");
        pessoaService.addNewPessoa(pedro, "0003");

    }
}
