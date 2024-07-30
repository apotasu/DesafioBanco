package com.example.demo.pessoa.config;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.service.PessoaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

@Autowired
public PessoaService pessoaService;
@PostConstruct
public void init(){
            Pessoa cath = new Pessoa("Catharina", "12345678900");
            Pessoa lari = new Pessoa("Larrissa", "00987654321234");
            Pessoa pedro = new Pessoa("Pedro", "12356712345");
            //repository.saveAll(List.of(cath, lari, pedro));

            pessoaService.addNewPessoa(cath);
            pessoaService.addNewPessoa(lari);
            pessoaService.addNewPessoa(pedro);
    }
}
