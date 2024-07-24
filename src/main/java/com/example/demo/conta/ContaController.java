package com.example.demo.conta;
import com.example.demo.pessoa.PessoaRepository;
import com.example.demo.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private PessoaRepository pessoaRepository;


    @PostMapping(path = "{pessoaId}")
    public void createAccount(@PathVariable("pessoaId") Long pessoaId, @RequestBody String agencia) {
        contaService.createAccount(pessoaRepository.findPessoasById(pessoaId), agencia);
    }

        @GetMapping
    public List<Conta> getContas(){
        return contaService.getContas();
    }
    @DeleteMapping(path = "{pessoaId}")
    public void deleteAnPessoa(@PathVariable("pessoaId") Long pessoaId){
        contaService.deletePessoa(pessoaId);
    }

}