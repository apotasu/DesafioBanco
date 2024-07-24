package com.example.demo.conta;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContaConfig {

    @Autowired
    public ContaRepository contaRepository;
    @PostConstruct
    public void init(){

    }
}
