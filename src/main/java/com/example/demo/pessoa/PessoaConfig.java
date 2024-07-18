package com.example.demo.pessoa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PessoaConfig {
    @Bean
    CommandLineRunner commandLineRunner(PessoaRepository repository) {
        return args -> {
            Pessoa cath = new Pessoa("Catharina", "12345678912", 1L);
            Pessoa lari = new Pessoa("Larissa", "12553378912", 2L);
            Pessoa pedro = new Pessoa("Catharina", "92345669912", 3L);
            repository.saveAll(List.of(cath, lari, pedro));
        };
    }
}
