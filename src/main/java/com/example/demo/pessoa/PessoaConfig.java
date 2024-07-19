package com.example.demo.pessoa;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PessoaConfig {

@Autowired
public PessoaService pessoaService;
@PostConstruct
public void init(){
            Pessoa cath = new Pessoa("Catharina", "12345678900", 1L);
            Pessoa lari = new Pessoa("Larrissa", "00987654321234", 2L);
            Pessoa pedro = new Pessoa("Pedro", "12356712345", 3L);
            //repository.saveAll(List.of(cath, lari, pedro));

            pessoaService.addNewPessoa(cath, "0001");
            pessoaService.addNewPessoa(lari, "0002");
            pessoaService.addNewPessoa(pedro, "0003");
    }
}
